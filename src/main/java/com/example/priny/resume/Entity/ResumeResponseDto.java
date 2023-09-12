package com.example.priny.resume.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class ResumeResponseDto {

    private Long id;
    private String userId;
    private String title;
    private List<String> projectList;
    private String detail;


    public ResumeResponseDto(){};

    public ResumeResponseDto(Long id, String userId, String title, List<String> projectList, String detail){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.projectList = projectList;
        this.detail = detail;
    }
}
