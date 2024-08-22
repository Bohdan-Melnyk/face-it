package com.example.faceit.repository;

import com.example.faceit.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(nativeQuery = true, value = "select location, count(*) from jobs group by location")
    List<Object[]> countJobByLocation();

    @Query(nativeQuery = true, value = "select * from jobs order by created_at desc limit 10")
    List<Job> recentJobs();
}
