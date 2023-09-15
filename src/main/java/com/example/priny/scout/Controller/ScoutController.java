package com.example.priny.scout.Controller;

import com.example.priny.config.CommonResponse;
import com.example.priny.scout.Entity.ScoutResponseDto;
import com.example.priny.scout.Entity.ScoutSaveRequestDto;
import com.example.priny.scout.Service.ScoutService;
import com.example.priny.scout.Service.ScoutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScoutController {

    private final ScoutServiceImpl scoutService;

    //받은 쪽지 조회
    @GetMapping("/receiver/{userId}")
    public List<ScoutResponseDto> getScoutByReceiver(@PathVariable String userId) {
        return scoutService.getScoutByReceiver(userId);
    }

    //보낸 쪽지 조회
    @GetMapping("/sender/{userId}")
    public List<ScoutResponseDto> getScoutBySender(@PathVariable String userId){
        return scoutService.getSenderMessages(userId);
    }

    //쪽지 저장
    @PostMapping("/scout")
    public ResponseEntity<CommonResponse> saveScout(@RequestBody ScoutSaveRequestDto scoutSaveRequestDto){
        scoutService.saveScout(scoutSaveRequestDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS", 200));
    }

//    쪽지 전체 삭제
    @DeleteMapping("/DeMassage/{userId}")
    public ResponseEntity<CommonResponse> deleteScout(@PathVariable String userId) {
        scoutService.deleteScout(userId);
        return ResponseEntity.ok(new CommonResponse("SUCCESS", 200));
    }
}

