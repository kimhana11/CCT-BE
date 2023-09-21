package com.example.priny.user.domain;

import com.example.priny.company.JobPosting;
import com.example.priny.resume.Entity.Resume;
import com.example.priny.scout.Entity.Scout;
import com.example.priny.user.DTO.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Column(length = 45)
    private String userId;
    @Column(length = 45)
    private String name; // 회원 성명
    @Column(length = 100)
    private String password; // 비밀번호
    private String phone; // 전화번호
    private String brith; //생년월일

    @Column(unique = true , length = 45)
    private String email; // 이메일

    private String address; // 주소

    private String cname;

    private String roles ;


    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Resume resume;

    //fetch = FetchType.EAGER,

    @JsonManagedReference
    @OneToMany(mappedBy = "user",  orphanRemoval = true)
    private List<JobPosting> jobPostings = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user",  orphanRemoval = true)
    private List<Scout> scoutList = new ArrayList<>();

    @Builder
    public User(String userId, String name, String password, String phone,
                String brith, String email, String address, String roles, String cname) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.brith = brith;
        this.email = email;
        this.address = address;
        this.roles = roles;
        this.cname = cname;
    }


    public static User createuser(UserDto userDto, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .brith(userDto.getBrith())
                .phone(userDto.getPhone())
                .cname(userDto.getCname())
                .password(passwordEncoder.encode(userDto.getPassword()))  //암호화처리
                .roles(userDto.getRoles())
                .build();
        return user;
    }

    public String getUserId(){
        return this.userId;
    }
    public void addUserAuthority(UserDto userDto) {
        this.roles = userDto.getRoles();
    }
//    public void encodePassword(PasswordEncoderConfig passwordEncoder){
//        this.password = PasswordEncoderConfig();
//    }
    public void setDeleted(boolean b) {
    }

}
