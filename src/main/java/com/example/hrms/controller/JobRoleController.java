package com.example.hrms.controller;

import com.example.hrms.exception.ResourceNotFoundException;
import com.example.hrms.model.JobRole;
import com.example.hrms.repository.JobRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/hrms/jobrole")
@CrossOrigin

public class JobRoleController {
    @Autowired
    private JobRoleRepository jobRoleRepository;

    @GetMapping("getJobRole/{id}")
    public ResponseEntity<JobRole> getJobRoleById(@PathVariable int id){
        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("JobRole not exist with id :"+id));
        return ResponseEntity.ok(jobRole);
    }

    @GetMapping("/list")
    public List<JobRole> getJobRoles(){
        return jobRoleRepository.findAll();
    }

    @PostMapping("/savejobrole")
    public JobRole createEmployee(@RequestBody JobRole jobRole){
        return jobRoleRepository.save(jobRole);
    }

    @PutMapping("updatejobrole/{id}")
    public ResponseEntity<JobRole> updateJobRole(@PathVariable int id,@RequestBody JobRole jobRoleDetails){
        JobRole updateJobRole = jobRoleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Job Role not exist with id :"+id));
        updateJobRole.setType(jobRoleDetails.getType());
        updateJobRole.setSalary(jobRoleDetails.getSalary());

        jobRoleRepository.save(updateJobRole);

        return ResponseEntity.ok(updateJobRole);
    }

    @DeleteMapping("deleteJobRole/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id){
        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Job Role not exist with id :"+id));

        jobRoleRepository.delete(jobRole);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
