package com.example.priny.scout.Entity;

import com.example.priny.resume.Entity.UserTest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender; // 발신자
    private String receiver; // 수신자
    private String title;
    private String message; // 내용


    @Builder
    public Scout(String sender, String receiver, String title, String message){
        this.sender = sender;
        this.receiver=receiver;
        this.title = title;
        this.message = message;
    }
}
