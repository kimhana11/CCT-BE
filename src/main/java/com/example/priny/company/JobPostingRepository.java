package com.example.priny.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    //List<JobPosting> findByUserId(String userId);
    List<JobPosting> findByUser_Id(Long id);
    List<JobPosting>  findByUser_UserId(String userId);
}
