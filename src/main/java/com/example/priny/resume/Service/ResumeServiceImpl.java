package com.example.priny.resume.Service;

import com.example.priny.resume.Entity.*;
import com.example.priny.resume.Repository.ResumeRepository;
import com.example.priny.resume.Repository.UserTestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ResumeRepository resumeRepository;
    private final UserTestRepository userTestRepository;


    //이력서 저장
    @Override
    public Long saveResume(String id, ResumeSaveRequesDto resumeSaveRequesDto){
        Optional<UserTest> userOptional = userTestRepository.findByUserId(id);
        if (!userOptional.isPresent()) {
            // 사용자가 존재하지 않는 경우 예외 처리
            throw new IllegalArgumentException("사용자가 존재하지 않습니다. id=" + id);
        }
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

    @Override
    //본인 이력서 조회
    public ResumeResponseDto getResumeByUserId(String userId) {
        UserTest user = userTestRepository.findByUserId(userId)
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
        UserTest user = userTestRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 않습니다. userId=" + userId));
        Resume resume = user.getResume();
        if(resume == null){
            throw new IllegalArgumentException("이력서가 존재하지 않습니다.");
        }
        resumeUpdateDto.update(resume);
        resumeRepository.save(resume);
    }

    //이력서 삭제
    @Override
    public void deleteResume(Long id) {
        UserTest user = userTestRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("유저를 찾을 수 없습니다. id=" + id));
        Resume resume = user.getResume();
        if(resume == null){
            throw new IllegalArgumentException("이력서가 존재하지 않습니다. userId=" + id);
        }
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
            logger.info("경력이 5년 이상입니다. 권한을 변경해주십시오.");
        }else if(years == 0){
            dto.setPeriodList(months+"개월");
        }
        dto.setPeriodList(years+"년");

        return dto;

    }

}
