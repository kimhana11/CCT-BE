package com.example.priny.config;


import lombok.AllArgsConstructor;
import lombok.Data;

//message : ‘SUCCESS’,code: 200
@Data
@AllArgsConstructor
public class CommonResponse<T> {
    String message;
    int code;
}
