package com.example.faceit.service.impl;

import com.example.faceit.dto.JobDto;
import com.example.faceit.entity.Job;
import com.example.faceit.mapper.JobDtoMapper;
import com.example.faceit.repository.JobRepository;
import com.example.faceit.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public Page<JobDto> getPaginatedJobs(int page, int size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        var jobs = jobRepository.findAll(pageable);
        return jobs.map(JobDtoMapper::mapToJobDto);
    }

    @Override
    public List<Object[]> getCountJobsByLocation() {
        return jobRepository.countJobByLocation();
    }

    @Override
    public List<JobDto> getRecentJobs() {
        var jobs = jobRepository.recentJobs();
        return JobDtoMapper.mapToJobDtoList(jobs);
    }

}
