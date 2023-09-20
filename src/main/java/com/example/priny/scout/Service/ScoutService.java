package com.example.priny.scout.Service;

import com.example.priny.resume.Entity.ResumeResponseDto;
import com.example.priny.resume.Entity.ResumeUpdateDto;
import com.example.priny.scout.Entity.Scout;
import com.example.priny.scout.Entity.ScoutResponseDto;
import com.example.priny.scout.Entity.ScoutSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ScoutService {

    //쪽지 저장
    public void saveScout( ScoutSaveRequestDto scoutSaveRequestDto);

    //받은 쪽지에서의 단일 조회
    public Optional<Scout> getScout(ScoutResponseDto scoutResponseDto);

    //받은 쪽지 리스트 조회
    public List<ScoutResponseDto> ScoutByReceiver(String receiverId);

    //보낸 쪽지 리스트 조회
    public List<ScoutResponseDto> ScoutBySender(String senderId);


    //보낸 쪽지 선택 삭제
   public void deleteScout(ScoutResponseDto userId);

//
     public void deleteAll(String userId);

}
