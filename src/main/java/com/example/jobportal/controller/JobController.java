package com.example.jobportal.controller;

import com.example.jobportal.model.Job;
import com.example.jobportal.model.User;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping("/jobs")
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs/list";
    }

    @GetMapping("/jobs/create")
    public String showCreateJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/create";
    }

    @PostMapping("/jobs/create")
    public String createJob(Job job, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        job.setUser(user);
        job.setPostedDate(LocalDateTime.now());
        jobService.save(job);
        return "redirect:/jobs";
    }
    
    @GetMapping("/jobs/{id}")
    public String getJobById(@PathVariable Long id, Model model) {
    	System.out.println("jobs/id");
        Job job = jobService.findJobById(id);
        if (job != null) {
            model.addAttribute("job", job);
            return "jobs/details";  // Return Thymeleaf template with job details
        } else {
            return "error/404";  // Return 404 page if job not found
        }
    }
}
