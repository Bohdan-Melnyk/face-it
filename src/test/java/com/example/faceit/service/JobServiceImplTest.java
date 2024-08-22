package com.example.faceit.service;

import com.example.faceit.entity.Job;
import com.example.faceit.repository.JobRepository;
import com.example.faceit.service.impl.JobServiceImpl;
import com.example.faceit.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JobServiceImplTest {

    @InjectMocks
    private JobServiceImpl jobServiceImpl;

    @Mock
    private JobRepository jobRepository;

    @Test
    void getPaginatedJobsTest() {
        Sort sort = Sort.by(Sort.Direction.fromString("asc"), "id");
        Pageable pageable = PageRequest.of(10, 10, sort);

        List<Job> testJobs = TestUtils.createJobList(10);

        Page<Job> jobPage = new PageImpl<>(testJobs);

        when(jobRepository.findAll(pageable)).thenReturn(jobPage);

        var result = jobServiceImpl.getPaginatedJobs(10, 10, "asc", "id");

        assertEquals(10L, result.getTotalElements());
    }

    @Test
    void getCountJobsByLocationTest() {
        when(jobRepository.countJobByLocation()).thenReturn(List.of(new Object[2], new Object[2]));

        jobRepository.countJobByLocation();

        verify(jobRepository, times(1)).countJobByLocation();
    }

    @Test
    void getRecentJobs() {
        when(jobRepository.recentJobs()).thenReturn(TestUtils.createJobList(10));

        var jobs = jobRepository.recentJobs();

        verify(jobRepository, times(1)).recentJobs();

        Assertions.assertEquals(10, jobs.size());
    }
}
