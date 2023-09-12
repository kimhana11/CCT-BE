package com.example.priny.resume.Service;

import com.example.priny.resume.Entity.*;
import com.example.priny.resume.Repository.ResumeRepository;
import com.example.priny.resume.Repository.UserTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserTestRepository userTestRepository;


    //이력서 저장
    @Override
    public Long saveResume(String id, ResumeSaveRequesDto resumeSaveRequesDto){

        UserTest user = userTestRepository.findByUserId(id).get();
        Resume resume = resumeSaveRequesDto.toEntity();
        resume.setUser(user);
        resumeRepository.save(resume);
        return resume.getId();

    }

    @Override
    //이력서 조회
    public List<ResumeResponseDto>getAllResume(){
        return resumeRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //본인 이력서 조회 ***수정 필요***8
    @Override
    public Optional<Resume> getResumeById(String userId){
        Optional<UserTest> userOptional = userTestRepository.findByUserId(userId);
        UserTest user = userOptional.get();
        Optional<Resume> resumeOptional = resumeRepository.findByUser(user);
        return resumeOptional;
    }

    //이력서 수정
    @Override
    public void editResume(Long id, ResumeUpdateDto resumeUpdateDto) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("이력서가 존재하지 않습니다. id=" + id));
        resume.update(resumeUpdateDto.getTitle(), resumeUpdateDto.getProjectList(), resumeUpdateDto.getDetail());
    }

    //이력서 삭제
    @Override
    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }

    private ResumeResponseDto mapToDTO(Resume resume) {
        ResumeResponseDto dto = new ResumeResponseDto();
        dto.setId(resume.getId());
        dto.setTitle(resume.getTitle());
        dto.setProjectList(resume.getProjectList());
        dto.setDetail(resume.getDetail());

        return dto;

    }

}
