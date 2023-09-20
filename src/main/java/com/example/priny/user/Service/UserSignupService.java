package com.example.priny.user.Service;

import com.example.priny.user.Config.CommonResponse;
import com.example.priny.user.DTO.UserCheck;
import com.example.priny.user.DTO.UserDto;
import com.example.priny.user.DTO.UserSignInDto;
import com.example.priny.user.DTO.UserSignInResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserSignupService {
    public Long signUp(UserDto userDto) throws Exception;

    public UserSignInResponseDto login(UserSignInDto userSignInDto);


    public ResponseEntity<CommonResponse> idCheck (UserCheck userCheck);
    public ResponseEntity<CommonResponse> emailCheck (UserCheck userCheck);
    public ResponseEntity<CommonResponse> phoneCheck (UserCheck userCheck);
}
