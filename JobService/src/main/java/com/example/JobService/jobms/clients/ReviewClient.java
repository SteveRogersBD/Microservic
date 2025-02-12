package com.example.JobService.jobms.clients;

import com.example.JobService.jobms.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE",
        url="${review-service.url}")
public interface ReviewClient {

    @GetMapping("/reviews/{id}")
    public Review getReviews(@PathVariable("id") Long id);
}
