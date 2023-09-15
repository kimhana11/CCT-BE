package com.example.priny.scout.Service;

import com.example.priny.resume.Entity.ResumeResponseDto;
import com.example.priny.resume.Entity.ResumeUpdateDto;
import com.example.priny.scout.Entity.Scout;
import com.example.priny.scout.Entity.ScoutResponseDto;
import com.example.priny.scout.Entity.ScoutSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoutService {

    //쪽지 저장
    public void saveScout(ScoutSaveRequestDto scoutSaveRequestDto);

    //받은 쪽지 조회
    public Scout getScoutById(Long id);

    //받은 쪽지 리스트 조회
    public List<ScoutResponseDto> getScoutByReceiver(String userId);

    //보낸 쪽지 리스트 조회
    public List<ScoutResponseDto> getSenderMessages(String senderId);

    //쪽지 삭제
    public void deleteScout(String userId);

}
