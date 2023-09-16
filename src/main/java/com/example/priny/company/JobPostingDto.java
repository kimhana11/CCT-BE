package com.example.priny.company;

import com.example.priny.resume.Entity.Resume;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPostingDto {

    private String userId;
    private String title;
    private String status;


    public JobPostingDto( String title, String status){
        this.title = title;
        this.status = status;
    }

    public JobPosting toEntity() {
        return JobPosting.builder().title(title).status(status).build();
    }
}
