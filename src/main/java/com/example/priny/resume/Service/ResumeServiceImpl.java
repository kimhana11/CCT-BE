package com.example.priny.resume.Service;


import com.example.priny.resume.Entity.Resume;
import com.example.priny.resume.Entity.ResumeResponseDto;
import com.example.priny.resume.Entity.ResumeSaveRequesDto;
import com.example.priny.resume.Entity.ResumeUpdateDto;
import com.example.priny.resume.Repository.ResumeRepository;
import com.example.priny.user.domain.User;
import com.example.priny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;


    //이력서 저장
    @Override
    public Long saveResume(String id, ResumeSaveRequesDto resumeSaveRequesDto){
        Optional<User> userOptional = userRepository.findByUserId(id);
        if (!userOptional.isPresent()) {
            // 사용자가 존재하지 않는 경우 예외 처리
            throw new IllegalArgumentException("사용자가 존재하지 않습니다. id=" + id);
        }
        User user = userRepository.findByUserId(id).get();
        Resume resume = resumeSaveRequesDto.toEntity();
        resume.setUser(user);
        resumeRepository.save(resume);
        return resume.getId();

    }

    @Override
    //이력서 조회
    public List<ResumeResponseDto> getAllResume(){
        return resumeRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    //본인 이력서 조회
    public ResumeResponseDto getResumeByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. userId=" + userId));
        Resume resume = user.getResume();
        if(resume == null){
            throw new IllegalArgumentException("이력서가 존재하지 않습니다.");
        }

        ResumeResponseDto resumeResponseDto = mapToDTO(resume);
        return resumeResponseDto;
    }

    //이력서 수정
    @Override
    public void editResume(String userId, ResumeUpdateDto resumeUpdateDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 않습니다. userId=" + userId));
        Resume resume = user.getResume();
        if(resume == null){
            throw new IllegalArgumentException("이력서가 존재하지 않습니다.");
        }
        resumeUpdateDto.update(resume);
        resumeRepository.save(resume);
    }

    //이력서 삭제
    @Override
    public void deleteResume(String userId) {
        Long id = userRepository.findByUserId(userId).get().getId();
        User user = userRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("유저를 찾을 수 없습니다. id=" + id));
        Resume resume = user.getResume();
        resumeRepository.deleteById(id);
    }

    private ResumeResponseDto mapToDTO(Resume resume) {
        ResumeResponseDto dto = new ResumeResponseDto();
        dto.setId(resume.getId());
        dto.setUserId(resume.getUser().getUserId());
        dto.setTitle(resume.getTitle());
        dto.setProjectList(resume.getProjectList());
        dto.setDetail(resume.getDetail());
        dto.setStackList(resume.getStackList());

        int sum = 0;

        for(String num:resume.getPeriodList()){
            int parsedNum = Integer.parseInt(num);
            sum += parsedNum;
        }
        //dto.setPeriodList(resume.getPeriodList());
        int years = sum / 12; // 개월 수를 년도로 변환
        int months = sum % 12; // 남은 개월 수

        if(years == 5){
            logger.info("경력이 5년 이상입니다. 권한을 변경하겠습니다.");
        }else if(years == 0){
            dto.setPeriodList(months+"개월");
        }
        dto.setPeriodList(years+"년");

        return dto;

    }

}
