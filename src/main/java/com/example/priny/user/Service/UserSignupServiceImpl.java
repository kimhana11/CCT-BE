package com.example.priny.user.Service;


import com.example.priny.user.Config.CommonResponse;
import com.example.priny.user.DTO.UserCheck;
import com.example.priny.user.DTO.UserDto;
import com.example.priny.user.DTO.UserSignInDto;
import com.example.priny.user.DTO.UserSignInResponseDto;
import com.example.priny.user.domain.User;
import com.example.priny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
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
        UserSignInResponseDto userSignInResponseDto = new UserSignInResponseDto();

            Optional<User> member = userRepository.findByUserId(userSignInDto.getUserId());

            List<String> roles = new ArrayList<>();
            roles.add(member.get().getRoles().name());
            userSignInResponseDto.setToken(TokenProvider.createToken(member.get().getUserId(), roles));
            userSignInResponseDto.setUserId(member.get().getUserId());
            userSignInResponseDto.setName(member.get().getName());
            userSignInResponseDto.setId(member.get().getId());

            return userSignInResponseDto;

    }


//    public void sendErrorResponse(int statusCode, String message) {
//        HttpServletResponse response = getResponseDto();
//    }
}

