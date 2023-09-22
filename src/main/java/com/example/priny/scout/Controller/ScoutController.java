package com.example.priny.scout.Controller;

import com.example.priny.config.CommonResponse;
import com.example.priny.scout.Entity.Scout;
import com.example.priny.scout.Entity.ScoutResponseDto;
import com.example.priny.scout.Entity.ScoutSaveRequestDto;
import com.example.priny.scout.Service.ScoutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scout")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ScoutController {

    private final ScoutServiceImpl scoutService;

    //쪽지 단일 조회
    @GetMapping("/read")
    public Optional<Scout> getSender(@RequestBody ScoutResponseDto scoutResponseDto) {
        return scoutService.getScout(scoutResponseDto);
    }

    //받은 쪽지 리스트 조회
    @GetMapping("/receiver/{receiverId}")
    public List<ScoutResponseDto> getScoutByReceiver(@PathVariable String receiverId) {
        return scoutService.ScoutByReceiver(receiverId);
    }

    //보낸 쪽지 리스트 조회
    @GetMapping("/sender/{senderId}")
    public List<ScoutResponseDto> getScoutBySender(@PathVariable String senderId){
        return scoutService.ScoutBySender(senderId);
    }

    //쪽지 저장
    @PostMapping("/save")
    public ResponseEntity<CommonResponse> saveScout(@RequestBody ScoutSaveRequestDto scoutSaveRequestDto){
        scoutService.saveScout( scoutSaveRequestDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS", 200));
    }

    //보낸 쪽지 선택 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse> deleteScout(@RequestBody ScoutResponseDto scoutResponseDto){
        scoutService.deleteScout(scoutResponseDto);
        return ResponseEntity.ok(new CommonResponse("SUCCESS", 200));
    }

////    쪽지 전체 삭제
//    @DeleteMapping("/deleteAll/{userId}")
//    public ResponseEntity<CommonResponse> deleteScout(@PathVariable String userId) {
//        scoutService.deleteAll(userId);
//        return ResponseEntity.ok(new CommonResponse("SUCCESS", 200));
//    }
}

