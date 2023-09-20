package com.example.priny.resume.Service;

import com.example.priny.company.JobPosting;
import com.example.priny.company.JobPostingDto;
import com.example.priny.resume.Entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {

    //이력서 저장
    public Long saveResume(String userId, ResumeSaveRequesDto resumeSaveRequesDto);

    //본인 이력서 조회
    public ResumeResponseDto getResumeByUserId(String userId);

    //이력서 전체 조회
    public List<ResumeResponseDto> getAllResume();

    //이력서 수정, 이력서가 존재하지 않을시 IllegalArgumentException
    public void editResume(String userId, ResumeUpdateDto resumeUpdateDto);

    //이력서 삭제
    public void deleteResume(String id);

}
