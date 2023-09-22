package com.example.priny.user.domain;


import com.example.priny.company.JobPosting;
import com.example.priny.resume.Entity.Resume;
import com.example.priny.scout.Entity.Scout;
import com.example.priny.user.DTO.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User extends TimeEntity implements UserDetails{

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

   // private String roles ;


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
                String brith, String email, String address, List<String> roles, String cname) {
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
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

}

