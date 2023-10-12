package com.project.micro.reports.service;

import com.project.micro.reports.integration.ReportResumeBalanceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReportService {

    Flux<ReportResumeBalanceResponse> getAccountFindAll(String idCustomer, String initial, String last);

}
