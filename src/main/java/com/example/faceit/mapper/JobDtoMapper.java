package com.example.faceit.mapper;

import com.example.faceit.dto.JobDto;
import com.example.faceit.entity.Job;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class JobDtoMapper {

    public static Job mapToJob(JobDto jobDto) {
        Job job = new Job();
        job.setSlug(jobDto.getSlug());
        job.setCompanyName(jobDto.getCompanyName());
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setRemote(jobDto.isRemote());
        job.setUrl(jobDto.getUrl());
        job.setTags(String.join(",", jobDto.getTags()));
        job.setJobTypes(String.join(",", jobDto.getJobTypes()));
        job.setLocation(jobDto.getLocation());
        job.setCreatedAt(LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(jobDto.getCreatedAt())), ZoneId.systemDefault()));
        return job;
    }

    public static JobDto mapToJobDto(Job job) {
        JobDto jobDto = new JobDto();
        jobDto.setSlug(job.getSlug());
        jobDto.setCompanyName(job.getCompanyName());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setRemote(job.isRemote());
        jobDto.setUrl(job.getUrl());
        jobDto.setTags(job.getTags().split(","));
        jobDto.setJobTypes(job.getJobTypes().split(","));
        jobDto.setLocation(job.getLocation());
        jobDto.setCreatedAt(String.valueOf(job.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        return jobDto;
    }

    public static List<JobDto> mapToJobDtoList(List<Job> jobs) {
        return jobs
                .stream()
                .map(JobDtoMapper::mapToJobDto)
                .toList();
    }

    public static List<Job> mapToJobList(List<JobDto> jobDtos) {
        return jobDtos
                .stream()
                .map(JobDtoMapper::mapToJob)
                .toList();
    }
}
