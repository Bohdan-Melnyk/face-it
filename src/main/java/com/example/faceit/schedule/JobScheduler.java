package com.example.faceit.schedule;

import com.example.faceit.dto.JobDtoList;
import com.example.faceit.entity.Job;
import com.example.faceit.mapper.JobDtoMapper;
import com.example.faceit.repository.JobRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    public static final String JOB_URL = "https://www.arbeitnow.com/api/job-board-api";

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final JobRepository jobRepository;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void fetchJobs() {
        String response = restTemplate.getForObject(JOB_URL, String.class);
        try {
            var result = objectMapper.readValue(response, JobDtoList.class);
            LOGGER.info("Successful mapping");
            var newJobs = JobDtoMapper.mapToJobList(result.getJobDtoList());
            jobRepository.deleteAll();
            jobRepository.saveAll(newJobs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
