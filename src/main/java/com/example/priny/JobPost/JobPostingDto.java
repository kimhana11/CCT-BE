package com.example.priny.JobPost;


import com.example.priny.JobPost.JobPosting;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
