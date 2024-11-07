package com.example.priny.resume.Entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeUpdateDto {

    private String title;
    private List<String> projectList;
    private String detail;
    private List<String> stackList;
    private List<String> periodList;

    public Resume update() {
        return Resume.builder().title(this.title)
                .projectList(this.projectList)
                .detail(this.detail)
                .stackList(this.stackList)
                .periodList(this.periodList)
                .build();
        }

    @Builder
    public ResumeUpdateDto(String title,  List<String> projectList, String detail, List<String> stackList, List<String> periodList){
        this.title = title;
        this.projectList = projectList;
        this.detail = detail;
        this.stackList = stackList;
        this.periodList = periodList;
    }

}
