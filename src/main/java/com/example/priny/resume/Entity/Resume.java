package com.example.priny.resume.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //한줄소개
    private String title;

    //상세 설명
    private String detail;

    //프로젝트 이력서 리스트
    @ElementCollection
    @JsonManagedReference
    //  @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<String> projectList;

    @ElementCollection
    @JsonManagedReference
    private List<String> stackList;

    @ElementCollection
    @JsonManagedReference
    private List<String> periodList;


    @OneToOne
    @JoinColumn(name = "user_id") // UserTest와 조인할 컬럼명 지정
    private UserTest user;

    @Builder
    public Resume(Long id, List<String> projecList, String detail,String title, List<String> stackList, List<String> periodList) {
        this.id = id;
        this.projectList = projecList;
        this.detail = detail;
        this.title = title;
        this.stackList = stackList;
        this.periodList = periodList;
    }


    public void update(String title, List<String> projectList, String detail) {
        this.title = title;
        this.projectList = projectList;
        this.detail = detail;
    }
}
