package com.example.faceit.controller;

import com.example.faceit.dto.JobDto;
import com.example.faceit.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<Page<JobDto>> getPaginatedJobs(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        return ResponseEntity.ok().body(jobService.getPaginatedJobs(page, size, direction, sortBy));
    }

    @GetMapping("/statistic-by-location")
    public ResponseEntity<List<Object[]>> getCountJobsByLocation() {
        return ResponseEntity.ok().body(jobService.getCountJobsByLocation());
    }

    @GetMapping("/recent-jobs")
    public ResponseEntity<List<JobDto>> getRecentJobs() {
        return ResponseEntity.ok().body(jobService.getRecentJobs());
    }
}
