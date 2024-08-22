package com.example.faceit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String slug;

    @Column
    private String companyName;

    @Column
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private boolean remote;

    @Column
    private String url;

    @Column
    private String tags;

    @Column
    private String jobTypes;

    @Column
    private String location;

    @Column
    private LocalDateTime createdAt;
}
