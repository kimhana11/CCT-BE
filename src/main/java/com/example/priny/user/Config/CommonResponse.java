package com.example.priny.user.Config;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CommonResponse <T>{
        String message;
        int code;
    }

