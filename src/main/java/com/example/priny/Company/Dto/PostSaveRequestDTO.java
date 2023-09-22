package com.example.priny.Company.Dto;

import com.example.priny.Company.Domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSaveRequestDTO {
    private Long id;

    private String userId;
    private String title;
    private String description;
    private String address; //회사 위치
    private String manager; //담당자
    private String career;  //경력 필터
    private List<String> periodList;  //프로젝트 기간
    private List<String>languageList;   //회사 필요로하는 컴퓨터언어

    @Builder
    public PostSaveRequestDTO(Long id, String userId, String title, String description, String manager ,String address, String career, List<String> languageList, List<String> periodList) {
        this.id=id;
        this.userId=userId;
        this.title=title; //생성자가 파라미터로 값을 받아 빌더로 받아온 값 초기화
        this.description=description;
        this.periodList=periodList;
        this.manager=manager;
        this.address=address;
        this.career=career;
        this.languageList=languageList;
    }

    //request dto로 받은 JobPost 객체를 entity화 하여 저장
    public Post toEntity() {
        return Post.builder().id(id).userId(userId).title(title).description(description).manager(manager).address(address).career(career).languageList(languageList).periodList(periodList).build();
    }
}
