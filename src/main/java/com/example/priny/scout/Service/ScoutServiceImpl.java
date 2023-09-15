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

    @Override
    public Scout getScoutById(Long id) {
        return scoutRepository.findById(id).orElse(null);
    }

    //수신측 받은 쪽지 조회
    @Override
    public List<ScoutResponseDto> getScoutByReceiver(String userId) {
        return scoutRepository.findByReceiver(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //발신측 보낸 쪽지 조회
    @Override
    public List<ScoutResponseDto> getSenderMessages(String senderId) {
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
        dto.setMassage(scout.getMassage());
        dto.setReceiver(scout.getReceiver());
        dto.setSender(scout.getSender());
        return dto;
    }
}
