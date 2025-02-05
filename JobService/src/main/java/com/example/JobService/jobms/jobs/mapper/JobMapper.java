package com.example.JobService.jobms.jobs.mapper;


import com.example.JobService.jobms.external.Company;
import com.example.JobService.jobms.external.Review;
import com.example.JobService.jobms.jobs.Job;
import com.example.JobService.jobms.jobs.JobDTO;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job,
                                                Company company,
                                                Review reviews)
    {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
