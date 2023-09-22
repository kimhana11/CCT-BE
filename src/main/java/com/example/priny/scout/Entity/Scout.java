package com.example.priny.scout.Entity;

import com.example.priny.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

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

    //발신자 userId
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Scout(String sender, String receiver, String title, String message, User user){
        this.sender = sender;
        this.receiver=receiver;
        this.title = title;
        this.message = message;
        this.user = user;
    }
}
