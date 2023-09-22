package com.example.priny.JobPost;

import com.example.priny.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

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
    private User user;


    @Builder
   public JobPosting(String title, String status){
        this.title = title;
        this.status = status;
    }

}
