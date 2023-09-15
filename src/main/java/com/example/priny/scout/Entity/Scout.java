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
    private String massage; // 내용

//    //받는사람
//    private String recipient;
//    private String message;

    //기업쪽이랑 매핑
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "company_id")
//    private Company company;

//프리랜서랑 매핑
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "user_id")
//    private UserTest user;


    @Builder
    public Scout(String sender, String receiver, String massage){
        this.sender = sender;
        this.receiver=receiver;
        this.massage = massage;
    }
}
