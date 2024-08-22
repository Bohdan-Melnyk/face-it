package com.example.faceit.utils;

import com.example.faceit.dto.JobDto;
import com.example.faceit.dto.JobDtoList;
import com.example.faceit.entity.Job;
import com.example.faceit.mapper.JobDtoMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TestUtils {

    private static final Random RANDOM = new Random();

    public static Job createJob() {
        Job job = new Job();
        job.setId(RANDOM.nextLong());
        job.setSlug("slug " + RANDOM.nextInt(10000));
        job.setCompanyName("name " + RANDOM.nextInt(10000));
        job.setTitle("title " + RANDOM.nextInt(10000));
        job.setDescription("description " + RANDOM.nextInt(10000));
        job.setRemote(false);
        job.setUrl("url " + RANDOM.nextInt(10000));
        job.setTags("tags " + RANDOM.nextInt(10000));
        job.setJobTypes("job types " + RANDOM.nextInt(10000));
        job.setLocation("Location " + RANDOM.nextInt(10000));
        job.setCreatedAt(LocalDateTime.now());
        return job;
    }

    public static List<Job> createJobList(int count) {
        return IntStream.range(0, count)
                .mapToObj(obj -> createJob())
                .toList();
    }

    public static List<JobDto> createNewJobsDto(List<Job> jobs) {
        return jobs
                .stream()
                .map(JobDtoMapper::mapToJobDto)
                .toList();
    }
}
