package com.example.priny.Company.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyUserSaveRequestDTO {
    String userId;
    String password;
    String email;
    String phoneNumber;
    String companyName;
    String address;


    public  CompanyUserSaveRequestDTO(String userId, String password, String email, String phoneNumber,String companyName, String address){
        this.userId=userId;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.companyName=companyName;
        this.address=address;
    }

}
