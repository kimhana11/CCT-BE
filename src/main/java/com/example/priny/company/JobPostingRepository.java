package com.example.priny.company;

import com.example.priny.resume.Entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    //List<JobPosting> findByUserId(String userId);
    List<JobPosting> findByUser_Id(Long id);
    List<JobPosting>  findByUser_UserId(String userId);
}
