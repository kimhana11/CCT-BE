package com.example.priny.Company.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostResponseDTO {
    private Long id;
    private String description;
    private String title;
    private String address; //회사주소
    private String manager; //담당자
    private List<String> periodList; //프로젝트 기간
    private List<String>languageList;   //회사 필요로하는 컴퓨터언어들


    public PostResponseDTO(){};

    public PostResponseDTO(Long id, String title, String description, String address, String manager, List<String> periodList, List<String> languageList){
        this.id=id;
        this.title=title;
        this.description=description;
        this.address=address;
        this.manager=manager;
        this.periodList=periodList;
        this.languageList=languageList;
    }

}