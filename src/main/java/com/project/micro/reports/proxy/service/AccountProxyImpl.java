package com.project.micro.reports.proxy.service;

import com.project.micro.reports.proxy.IAccountProxy;
import com.project.micro.reports.proxy.bean.AccountBean;
import com.project.micro.reports.utils.ExternalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountProxyImpl implements IAccountProxy {

    @Autowired
    private ExternalProperties p;
    @Override
    public Flux<AccountBean> findIdCustomerDateBetween(String idCustomer,String initial, String last) {
        WebClient webClient= WebClient.builder().baseUrl(p.urlBankAccount).build();
        return webClient.get()
                .uri("/findIdCustomerDateBetween/{idCustomer}/{initial}/{last}", idCustomer, initial, last)
                .retrieve()
                .bodyToFlux(AccountBean.class);
    }
}
