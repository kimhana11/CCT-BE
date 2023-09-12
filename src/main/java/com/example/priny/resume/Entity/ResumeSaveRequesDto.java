package com.example.priny.resume.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeSaveRequesDto {

    //  private String userId;
    private String title;
    private String detail;
    private List<String> projectList;

    @Builder
    public ResumeSaveRequesDto(String title, List<String> projectList, String detail) {
        //   this.userId = userId;
        this.title = title;
        this.detail = detail;
        this.projectList = projectList;
    }

    //request dto로 받은 Resume 객체를 entity화 하여 저장
    public Resume toEntity() {
      return Resume.builder().detail(detail).title(title).projecList(projectList).build();
    }

}
