package com.example.priny.scout.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScoutSaveRequestDto {

    private String sender; // 발신자
    private String receiver; // 수신자
    private String massage; // 내용

    public Scout toEntity(){
        return Scout.builder().sender(sender).receiver(receiver).massage(massage).build();
    }
}
