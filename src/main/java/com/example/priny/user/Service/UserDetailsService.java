package com.example.priny.user.Service;


import com.example.priny.user.domain.UserDetails;
import com.example.priny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }

        public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        logger.info("[loadUserByUsername] loadUserByUsername 수행. username: {} ", username);

        return userRepository.findByUserId(username).orElse(null);
    }
}
