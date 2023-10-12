package com.project.micro.reports.mapper;

import com.project.micro.reports.dto.ReportResumeBalanceDto;
import com.project.micro.reports.integration.ReportResumeBalanceResponse;
import org.springframework.beans.BeanUtils;

public class ReportMapper {

    public static ReportResumeBalanceResponse toAccountModelRes(ReportResumeBalanceDto reportResumeBalanceDto){

        ReportResumeBalanceResponse reportResumeBalanceResponse=new ReportResumeBalanceResponse();
        BeanUtils.copyProperties(reportResumeBalanceDto,reportResumeBalanceResponse);
        return reportResumeBalanceResponse;
    }
}
