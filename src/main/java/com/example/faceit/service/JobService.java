package com.example.faceit.service;

import com.example.faceit.dto.JobDto;
import com.example.faceit.entity.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {

    Page<JobDto> getPaginatedJobs(int page, int size, String direction, String sortBy);

    List<Object[]> getCountJobsByLocation();

    List<JobDto> getRecentJobs();
}
