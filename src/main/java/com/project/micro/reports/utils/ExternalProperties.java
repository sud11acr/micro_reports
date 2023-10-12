package com.project.micro.reports.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalProperties {

    @Value("${url.bank.account}")
    public String urlBankAccount;
}
