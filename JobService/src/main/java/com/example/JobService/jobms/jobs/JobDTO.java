package com.example.JobService.jobms.jobs;

import com.example.JobService.jobms.external.Company;
import com.example.JobService.jobms.external.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private Review reviews;

}
