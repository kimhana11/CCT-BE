package com.example.priny.Company.Domain;

import com.example.priny.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
@Entity
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userId;
    private String title; //제목
    private String description; //설명
    private String address;  //회사 주소
    private String career;  //경력 필터
    private String manager; //담당 매니저

    @ElementCollection
    @JsonManagedReference
    private List<String> languageList; //사용언어

    @ElementCollection
    @JsonManagedReference
    private List<String> periodList;    //기간

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user")
    private User user;

    @Builder // 생성자를 만든 후 그 위에 @Builder 애노테이션 적용
    public Post(Long id, String userId, String title, String description, String address, String career, String manager, List<String> languageList, List<String> periodList){
        this.id=id;
        this.userId=userId;
        this.title = title; //생성자가 파라미터로 값을 받아 빌더로 받아온 값 초기화
        this.description= description;
        this.address = address;
        this.periodList = periodList;
        this.career = career;
        this.manager=manager;
        this.languageList= languageList;

    }

}