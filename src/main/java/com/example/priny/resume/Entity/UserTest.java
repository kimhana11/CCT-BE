package com.example.priny.resume.Entity;

import com.example.priny.company.JobPosting;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTest {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String role;

   // @JsonManagedReference
   // @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    //  private List<Resume> resumeList = new ArrayList<>();
    
    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Resume resume;


    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<JobPosting> jobPostings = new ArrayList<>();

    public String getUserId(){
        return this.userId;
    }

}


