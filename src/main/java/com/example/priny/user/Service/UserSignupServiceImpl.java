package com.example.priny.user.Service;


import com.example.priny.user.Config.CommonResponse;
import com.example.priny.user.DTO.UserCheck;
import com.example.priny.user.DTO.UserDto;
import com.example.priny.user.DTO.UserSignInDto;
import com.example.priny.user.DTO.UserSignInResponseDto;
import com.example.priny.user.domain.User;
import com.example.priny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import com.example.priny.user.Config.PasswordEncoderConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserSignupServiceImpl implements UserSignupService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    Logger logger = LoggerFactory.getLogger(this.getClass());
 //   private final PasswordEncoderConfig passwordEncoder;

    @Transactional
    @Override
    public Long signUp(UserDto userDto) throws Exception {
        Optional<User> testUser = userRepository.findByUserId(userDto.getUserId());
        User user = userDto.toEntity();
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.addUserAuthority(userDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public ResponseEntity<CommonResponse> idCheck(UserCheck userCheck) {
        try {
            Optional<User> checkUser = userRepository.findByUserId(userCheck.getUserCheck());
        } catch (IllegalArgumentException il) {
            return ResponseEntity.ok(new CommonResponse("FAIL", 403));
        }  return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    @Override
    public ResponseEntity<CommonResponse> emailCheck(UserCheck userCheck) {
        try {
            Optional<User> checkUser = userRepository.findByEmail(userCheck.getUserCheck());
        } catch (IllegalArgumentException il) {
            return ResponseEntity.ok(new CommonResponse("FAIL", 403));
        }return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }

    @Override
    public ResponseEntity<CommonResponse> phoneCheck(UserCheck userCheck) {
        try {
            Optional<User> checkUser = userRepository.findByPhone(userCheck.getUserCheck());
        } catch (IllegalArgumentException il) {
            return ResponseEntity.ok(new CommonResponse("FAIL", 403));
        }return ResponseEntity.ok(new CommonResponse("SUCCESS",200));
    }



    @Override
    public UserSignInResponseDto login(UserSignInDto userSignInDto) {

        //userId로 유저 조회
        User user = userRepository.findByUserId(userSignInDto.getUserId()).get();
        logger.info("[getSignInResult] Id : {}", userSignInDto.getUserId());

        logger.info("[getSignInResult] SignInResultDto 객체 생성");
        UserSignInResponseDto userSignInResponseDto =  UserSignInResponseDto.builder()
                .token(tokenProvider.createToken(String.valueOf(user.getUserId()),
                        user.getRoles())).
                userId(userSignInDto.getUserId())
                .id(user.getId())
                .name(user.getName())
                .build();

        logger.info("[getSignInResult] getSignInResult 객체에 값 주입");

        return userSignInResponseDto;
    }


//    public void sendErrorResponse(int statusCode, String message) {
//        HttpServletResponse response = getResponseDto();
//    }
}

