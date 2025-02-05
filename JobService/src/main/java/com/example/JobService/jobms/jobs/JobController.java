package com.example.JobService.jobms.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobServiceIml jobService;

    List<Job> jobs;
    @GetMapping("/get-all")
    public ResponseEntity<List<JobDTO>> getAllJobs()
    {
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        jobService.createJob(job);
        return new ResponseEntity<>("Job created",HttpStatus.CREATED);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id)
    {
        JobDTO job = jobService.findById(id);
        if(job==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        boolean deleted = jobService.deleteById(id);
        if (deleted)
        {
            return new ResponseEntity<>("Job deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<String>updateById(@PathVariable Long id,
                                            @RequestBody Job job)
    {
        boolean flag = jobService.updateJob(id, job);
        if(flag) return new ResponseEntity<>("Job updated",HttpStatus.OK);
        return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete-all")
    public ResponseEntity<String>deleteAll()
    {
        jobService.deleteAll();
        return new ResponseEntity<>("All jobs deleted",HttpStatus.OK);
    }

}
