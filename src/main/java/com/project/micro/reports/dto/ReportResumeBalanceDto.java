package com.project.micro.reports.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Generated
public class ReportResumeBalanceDto {

    private BigDecimal balanceDaily;
    private String accountType;

}
