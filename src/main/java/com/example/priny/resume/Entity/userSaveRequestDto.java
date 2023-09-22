package com.example.priny.resume.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class userSaveRequestDto {
    String userId;
    String password;

    public userSaveRequestDto(String id, String password){
        this.userId=id;
        this.password=password;
    }
}
