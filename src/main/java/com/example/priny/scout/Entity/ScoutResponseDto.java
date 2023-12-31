package com.example.priny.scout.Entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutResponseDto {

    private Long id;
    private String sender; // 발신자
    private String receiver; // 수신자
    private String title;
    private String message; // 내용

    public ScoutResponseDto(){};

    public ScoutResponseDto(String sender, String receiver,String title, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.message = message;
    }
}
