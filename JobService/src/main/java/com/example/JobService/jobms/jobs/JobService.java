package com.example.JobService.jobms.jobs;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO findById(Long id);
    boolean deleteById(Long id);
    boolean updateJob(Long id, Job updatedJob);
    void deleteAll();
}
