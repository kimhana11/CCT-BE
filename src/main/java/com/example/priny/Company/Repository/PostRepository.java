package com.example.priny.Company.Repository;


import com.example.priny.Company.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByUserId(Long id);

    //  List<JobPost> findAll();

//     DTO로 변환하여 반환하는 메서드
//    List<JobPostResponseDTO> findAllAsDTO();
//
//    // 새로운 JobPost를 저장하는 메서드
//    JobPost save(JobPostSaveRequestDTO jobPostSaveRequestDTO);
//
//     기존 JobPost를 업데이트하는 메서드
//    JobPost edit(Long id, JobPostEditDTO jobPostEditDTO);

//     JobPost를 삭제하는 메서드
//    void deleteById(Long id);
}