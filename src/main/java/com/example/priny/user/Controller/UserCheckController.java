package com.example.priny.user.Controller;


import com.example.priny.config.CommonResponse;
import com.example.priny.user.DTO.UserCheck;
import com.example.priny.user.Service.UserService;
import com.example.priny.user.Service.UserSignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/user")
public class UserCheckController {

    private final UserSignupService userSignupService;
    private final UserService userService;

    @PostMapping("/idCheck")
    public ResponseEntity<CommonResponse> idCheck(@RequestBody UserCheck userCheck){
        return userSignupService.idCheck(userCheck);
    }

    @PostMapping("/emailCheck")
    public ResponseEntity<CommonResponse> emailCheck(@RequestBody UserCheck userCheck){
        return userSignupService.emailCheck(userCheck);
    }

    @PostMapping("/phoneCheck")
    public ResponseEntity<CommonResponse> phoneCheck(@RequestBody UserCheck userCheck){
        return userSignupService.phoneCheck(userCheck);
    }
}
