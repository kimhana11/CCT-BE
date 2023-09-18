package com.example.priny.scout.Service;

import com.example.priny.resume.Repository.UserTestRepository;
import com.example.priny.scout.Entity.Scout;
import com.example.priny.scout.Entity.ScoutResponseDto;
import com.example.priny.scout.Entity.ScoutSaveRequestDto;
import com.example.priny.scout.Repository.ScoutRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService{

    private final ScoutRepository scoutRepository;

    @Override
    public void saveScout(ScoutSaveRequestDto scoutSaveRequestDto) {
        Scout scout = scoutSaveRequestDto.toEntity();
        scoutRepository.save(scout);
    }

    //받은 쪽지 단일 조회(발신자 id로 조회)
    @Override
    public Optional<Scout>  getScoutBySender(String senderId) {
        return scoutRepository.findOneSender(senderId);
    }

    //보낸 쪽지 단일 조회 (수신자 id로 조회 (내가 보낸 사람))
    @Override
    public Optional<Scout>  getScoutByReceiver(String receiverId) {
        return scoutRepository.findOneReceiver(receiverId);
    }

    //수신측 받은 쪽지 조회
    @Override
    public List<ScoutResponseDto> ScoutByReceiver(String receiverId) {
        return scoutRepository.findByReceiver(receiverId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //발신측 보낸 쪽지 조회
    @Override
    public List<ScoutResponseDto> ScoutBySender(String senderId) {
        return scoutRepository.findBySender(senderId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //받은 쪽지 삭제
    @Override
    public void deleteScout(String userId) {
        List<Scout> scoutReceiver = scoutRepository.findByReceiver(userId);
        // 받은 쪽지가 존재하면 쪽지 전부 삭제
        if (!scoutReceiver.isEmpty()) {
            scoutRepository.deleteAll(scoutReceiver);
        }
    }

    private ScoutResponseDto mapToDTO(Scout scout) {
        ScoutResponseDto dto = new ScoutResponseDto();
        dto.setTitle(scout.getTitle());
        dto.setMessage(scout.getMessage());
        dto.setReceiver(scout.getReceiver());
        dto.setSender(scout.getSender());
        return dto;
    }
}
