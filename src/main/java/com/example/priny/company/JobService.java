package com.example.priny.company;


import java.util.List;

public interface JobService {
    //지원 저장
    public void saveApply(String userId,JobPostingDto jobPostingDto);
    //지원 리스트 조회
    public List<JobPosting> getAppliedJobPostings(String id);
}
