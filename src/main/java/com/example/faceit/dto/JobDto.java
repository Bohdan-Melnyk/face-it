package com.example.faceit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"tags", "jobTypes"})
public class JobDto {
    @JsonProperty("slug")
    private String slug;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("remote")
    private boolean remote;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tags")
    private String[] tags;

    @JsonProperty("job_types")
    private String[] jobTypes;

    @JsonProperty("location")
    private String location;

    @JsonProperty("created_at")
    private String createdAt;
}
