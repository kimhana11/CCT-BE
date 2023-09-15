package com.example.priny.scout.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScoutResponseDto {

    private String sender; // 발신자
    private String receiver; // 수신자
    private String massage; // 내용

    public ScoutResponseDto(){};

    public ScoutResponseDto(String sender, String receiver, String massage){
        this.sender = sender;
        this.receiver = receiver;
        this.massage = massage;
    }
}
