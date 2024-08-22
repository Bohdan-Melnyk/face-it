package com.example.faceit.controller;

import com.example.faceit.dto.JobDto;
import com.example.faceit.service.impl.JobServiceImpl;
import com.example.faceit.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@WebMvcTest(JobController.class)
public class JobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public JobControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private JobServiceImpl jobService;

    @Test
    void getPaginatedJobsTest() throws Exception {
        var jobs = TestUtils.createJobList(10);
        var jobsDto = TestUtils.createNewJobsDto(jobs);
        Page<JobDto> jobDtoPage = new PageImpl<>(jobsDto,
                PageRequest.of(0, 10, Sort.Direction.ASC, "id"), jobsDto.size());

        when(jobService.getPaginatedJobs(0, 10, "asc", "id")).thenReturn(jobDtoPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobs")
                        .param("page", "0")
                        .param("size", "10")
                        .param("direction", "asc")
                        .param("sortBy", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCountJobsByLocationTest() throws Exception {
        Object[] first = new Object[]{"Berlin", 3};
        Object[] second = new Object[]{"Munich", 5};

        when(jobService.getCountJobsByLocation()).thenReturn(List.of(first, second));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/statistic-by-location")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getRecentJobsTest() throws Exception {
        var jobs = TestUtils.createJobList(10);
        when(jobService.getRecentJobs()).thenReturn(TestUtils.createNewJobsDto(jobs));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/recent-jobs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
