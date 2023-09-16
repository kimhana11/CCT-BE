package com.example.priny.company;

import com.example.priny.resume.Entity.UserTest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//접근 레벨 p
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String status;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private UserTest user;


    @Builder
   public JobPosting(String title, String status){
        this.title = title;
        this.status = status;
    }

}
