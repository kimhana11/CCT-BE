package com.example.priny.user.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class UserSignInResponseDto {

    private Long id;
    private String userId;
    private String name;
    private String token;

    @Builder
    UserSignInResponseDto(Long id, String userId, String name, String token){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.token = token;
    }
}
