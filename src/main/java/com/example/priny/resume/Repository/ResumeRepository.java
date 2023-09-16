package com.example.priny.resume.Repository;

import com.example.priny.resume.Entity.Resume;
import com.example.priny.resume.Entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
//    Optional<Resume> findByUser(UserTest user);
//    Resume findByUserId(String userId);

}
