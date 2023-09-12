package com.example.priny.resume.Repository;

import com.example.priny.resume.Entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {
    Optional<UserTest> findByUserId(String userId);
}
