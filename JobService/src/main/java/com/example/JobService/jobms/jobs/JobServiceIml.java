package com.example.JobService.jobms.jobs;


import com.example.JobService.jobms.clients.CompanyClient;
import com.example.JobService.jobms.clients.ReviewClient;
import com.example.JobService.jobms.external.Company;
import com.example.JobService.jobms.external.Review;
import com.example.JobService.jobms.jobs.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceIml implements JobService {

    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepo.findAll();

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public void createJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public JobDTO findById(@PathVariable Long id)
    {
        Job job = jobRepo.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            jobRepo.deleteById(id);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> job1 = jobRepo.findById(id);
            if(job1.isPresent())
            {
                Job job = job1.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setCompanyId(updatedJob.getCompanyId());
                jobRepo.save(job);
                return true;
            }
        return false;
    }

    private JobDTO convertToDTO(Job job)
    {
//        Company company = restTemplate.
//                getForObject("http://COMPANY-SERVICE:8081/company/get/id/"
//                +job.getCompanyId(), Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());
//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews/" + job.getCompanyId()
//                , HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
        Review reviews = reviewClient.getReviews(job.getCompanyId());
        //List<Review>reviews = reviewResponse.getBody();
        JobDTO dto = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
        return dto;
    }
    public void deleteAll()
    {
        jobRepo.deleteAll();
    }


}
