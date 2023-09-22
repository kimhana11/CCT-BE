package com.example.priny.user.Config;

import com.example.priny.user.domain.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getLoginUsername(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getUsername();
    }
}
