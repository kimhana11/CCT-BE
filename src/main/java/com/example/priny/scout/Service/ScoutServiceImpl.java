package com.example.priny.scout.Service;


import com.example.priny.scout.Entity.Scout;
import com.example.priny.scout.Entity.ScoutResponseDto;
import com.example.priny.scout.Entity.ScoutSaveRequestDto;
import com.example.priny.scout.Repository.ScoutRepository;
import com.example.priny.user.domain.User;
import com.example.priny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService{

    private final ScoutRepository scoutRepository;
    private final UserRepository userRepository;

    @Override
    public void saveScout( ScoutSaveRequestDto scoutSaveRequestDto) {
        User user = userRepository.findByUserId(scoutSaveRequestDto.getSender()).get();
        Scout scout = scoutSaveRequestDto.toEntity();
        scout.setUser(user);
        scoutRepository.save(scout);
    }

    //Long id로 쪽지 단일 조회
    @Override
    public Optional<Scout> getScout(ScoutResponseDto scoutResponseDto) {
        Optional<Scout> scout = scoutRepository.findById(scoutResponseDto.getId());
        return scoutRepository.findById(scout.get().getId());
    }


    //수신측 받은 쪽지 조회 리스트
    @Override
    public List<ScoutResponseDto> ScoutByReceiver(String receiverId) {
        return scoutRepository.findByReceiver(receiverId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //발신측 보낸 쪽지 조회 리스트
    @Override
    public List<ScoutResponseDto> ScoutBySender(String senderId) {
        return scoutRepository.findByReceiver(senderId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    //쪽지 선택 삭제
    @Override
    public void deleteScout(ScoutResponseDto scoutResponseDto) {
        Optional<Scout> scout = scoutRepository.findById(scoutResponseDto.getId());
            scoutRepository.deleteById(scout.get().getId());
        }


    //쪽지 전체 삭제
    @Override
    public void deleteAll(String userId) {
            scoutRepository.deleteAll();
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
