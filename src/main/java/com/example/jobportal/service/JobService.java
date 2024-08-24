package com.example.jobportal.service;

import com.example.jobportal.model.Job;
import com.example.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> findByUserId(Long userId) {
        return jobRepository.findByUserId(userId);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }
    
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
}
