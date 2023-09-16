package com.example.priny.company;


import com.example.priny.config.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@Component
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;


    @PostMapping("/apply")
    public ResponseEntity<CommonResponse> saveJob(@RequestBody JobPostingDto jobPostingDto){
        jobService.saveApply(jobPostingDto.getUserId(), jobPostingDto );
        return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    @GetMapping("/checkPost/{userId}")
    public List<JobPosting> getAppliedJobPostings(@PathVariable String userId){
        return jobService.getAppliedJobPostings(userId);
    }
}
