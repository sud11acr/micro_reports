package com.project.micro.reports.integration;

import com.project.micro.reports.dto.ReportResumeBalanceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResumeBalanceResponse {

    private BigDecimal balanceDaily;
    private String accountType;
}
