package com.project.micro.reports.proxy;

import com.project.micro.reports.proxy.bean.AccountBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountProxy {

    Flux<AccountBean> findIdCustomerDateBetween(String idCustomer,String initial, String last);
}
