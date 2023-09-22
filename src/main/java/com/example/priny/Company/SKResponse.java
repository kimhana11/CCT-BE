package com.example.priny.Company;
import lombok.Getter;

@Getter
public class SKResponse {

    String message;
    String status;
    public SKResponse(){}
    public SKResponse(String message, String status){
        this.message = message;
        this.status = status;
    }
}