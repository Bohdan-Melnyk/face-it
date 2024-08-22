package com.example.faceit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDtoList {

    @JsonProperty("data")
    List<JobDto> jobDtoList;
}
