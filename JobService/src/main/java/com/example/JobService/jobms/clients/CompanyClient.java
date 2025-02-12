package com.example.JobService.jobms.clients;

import com.example.JobService.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "COMPANY-SERVICE",
        url="${company-service.url}")
public interface CompanyClient {
    @GetMapping("company/get/id/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
