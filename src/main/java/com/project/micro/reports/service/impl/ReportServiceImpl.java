package com.project.micro.reports.service.impl;

import com.project.micro.reports.integration.ReportResumeBalanceResponse;
import com.project.micro.reports.proxy.IAccountProxy;
import com.project.micro.reports.proxy.bean.AccountBean;
import com.project.micro.reports.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

public class ReportServiceImpl implements IReportService {

    @Autowired
    private IAccountProxy proxy;
    @Override
    public Flux<ReportResumeBalanceResponse> getAccountFindAll(String idCustomer, String initial, String last) {

        Flux<AccountBean> transactions = (Flux<AccountBean>) proxy.findIdCustomerDateBetween(idCustomer, initial, last).subscribe();

        // Agrupar transacciones por tipo de cuenta o producto de crédito
        Flux<GroupedFlux<String, AccountBean>> groupedByAccountType = transactions.groupBy(AccountBean::getAccountType);

        // Calcular el saldo promedio diario para cada grupo
        Flux<ReportResumeBalanceResponse> reportResponses = groupedByAccountType
                .flatMap(group -> {
                    String accountType = group.key();

                    // Calcular el saldo total al final de cada día
                    Flux<BigDecimal> dailyBalances = group
                            .collectMultimap(AccountBean::getModificationDate, AccountBean::getBalance)
                            .flatMapIterable(dailyAmounts -> {
                                BigDecimal dailyBalance = BigDecimal.ZERO;
                                for (BigDecimal amount : dailyAmounts.values()) {
                                    dailyBalance = dailyBalance.add(amount);
                                }
                                return Collections.singletonList(dailyBalance);
                            });


                    // Calcular el saldo promedio diario para el mes en curso
                    return dailyBalances
                            .collectList()
                            .map(dailyBalancesList -> {
                                BigDecimal totalBalance = BigDecimal.ZERO;
                                for (BigDecimal balance : dailyBalancesList) {
                                    totalBalance = totalBalance.add(balance);
                                }
                                BigDecimal averageBalance = totalBalance.divide(BigDecimal.valueOf(dailyBalancesList.size()), RoundingMode.HALF_UP);
                                return new ReportResumeBalanceResponse(averageBalance,accountType);
                            });
                });

        return reportResponses;


/*
        return proxy.findIdCustomerDateBetween(idCustomer,initial, last)
                .groupBy(repor->repor.getAccountType())
                .flatMap(groupAccountType->groupAccountType
                        .collectList()
                        .map(transactions->transactions.)
                );

 */
    }
}
