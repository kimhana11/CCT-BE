package com.example.priny.company;

import com.example.priny.resume.Entity.UserTest;
import com.example.priny.resume.Repository.UserTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Service
@RequiredArgsConstructor
@Repository
public class JobServiceImpl implements JobService{

    private final UserTestRepository userTestRepository;
    private final JobPostingRepository jobPostingRepository;

    @Override
    @Transactional
    public void saveApply(String id, JobPostingDto jobPostingDto){
        UserTest user = userTestRepository.findByUserId(id).get();
        JobPosting jobPosting = jobPostingDto.toEntity();
        jobPosting.setUser(user);
        jobPostingRepository.save(jobPosting);
    }

    //지원 내역 리스트 조회
    @Override
    public List<JobPosting> getAppliedJobPostings(String userId){
        //   List<JobPostingDto> jobPostingDtoList = new ArrayList<>();
        //유저 조회
       // UserTest user = userTestRepository.findByUserId(userId)
         //       .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. userId=" + userId));

        //공고 정보를 조회
      //  List<JobPostingDto> jobPostingDtoList = jobPostingRepository.findJobPostingDtoList(user.getId());
        return jobPostingRepository.findByUser_UserId(userId);

    }

}
