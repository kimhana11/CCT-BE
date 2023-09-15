package com.example.priny.resume.Controller;

import com.example.priny.config.CommonResponse;
import com.example.priny.resume.Entity.ResumeResponseDto;
import com.example.priny.resume.Entity.ResumeSaveRequesDto;
import com.example.priny.resume.Entity.ResumeUpdateDto;
import com.example.priny.resume.Service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.priny.resume.Entity.Resume;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PatchMapping("/resume/{userId}")
    public ResponseEntity<CommonResponse> editResume(@PathVariable String userId, @RequestBody ResumeUpdateDto resumeUpdateDto){
        resumeService.editResume(userId, resumeUpdateDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    @DeleteMapping("/resume/{id}")
    public ResponseEntity<CommonResponse> deleteResume(@PathVariable Long id){
        resumeService.deleteResume(id);
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    //이력서 저장
    @PostMapping("/resume")
    public ResponseEntity<CommonResponse> saveResume(@RequestBody ResumeSaveRequesDto resumeSaveRequesDto){
        resumeService.saveResume(resumeSaveRequesDto.getUserId(), resumeSaveRequesDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }
    //이력서 전체 조회
    @GetMapping("/resumes")
    public List<ResumeResponseDto> resumes(){
        return resumeService.getAllResume();
    }


    //본인 이력서 조회
    @GetMapping("resume/{userId}")
    ResumeResponseDto myResume2(@PathVariable String userId){
        return resumeService.getResumeByUserId(userId);
    }

}

