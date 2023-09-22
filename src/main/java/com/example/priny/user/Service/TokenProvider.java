package com.example.priny.user.Service;


import com.example.priny.user.Config.ErrorCode;
import com.example.priny.user.domain.UserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class TokenProvider {
   // private static String key = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK";
   private final String secretKey;
    Logger logger = LoggerFactory.getLogger(this.getClass());
//    private long tokenValidTime = 1440 * 60 * 7 * 1000L;
      private long validityInMilliseconds = 1000L * 60 * 60 ;

      UserDetailsService userDetailsService;
    public TokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey, UserDetailsService userDetailsService) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        //this.validityInMilliseconds = validityInMilliseconds;
        this.userDetailsService = userDetailsService;
    }

  //  @PostConstruct
 //   protected void init(){
 //       key = Base64.getEncoder().encodeToString(key.getBytes());
 //   }

    //토큰 생성
    public String createToken(String userId, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles",roles); // 정보는 키. 벨류로 저장
        //Date now = new Date();
        Date now = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey ) // 사용할 암호화 알고리즘
        // signature에 들어갈 key 값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        logger.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));

        logger.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 값 추출
    public String getUsername(String token) {
        logger.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        //secretKey 설정, sub 값 추출
        String info = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();

        logger.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        return info;

    }
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    //유효한 토큰인지 확인
    public boolean validateToken(String jwtToken){
        logger.info("[validateToken] 토큰 유효 체크 시작");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());

        }catch (SecurityException | MalformedJwtException e) {
            logger.info("{}", ErrorCode.WRONG_TYPE_TOKEN.getCode());
            return false;
        } catch (ExpiredJwtException e) {
            logger.info("{}",ErrorCode.EXPIRED_TOKEN.getCode());
            return false;
        } catch (UnsupportedJwtException e) {
            logger.info("{}",ErrorCode.UNSUPPORTED_TOKEN.getCode());
            return false;
        } catch (IllegalArgumentException e) {
            logger.info("{}",ErrorCode.WRONG_TYPE_TOKEN.getCode());
            return false;
        } catch (Exception e) {
            logger.info("{}",ErrorCode.UNKNOWN_ERROR.getCode());
            logger.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
//        catch (Exception e) {
//            logger.info("[validateToken] 토큰 유효 체크 예외 발생");
//            return false;
//        }
    }


}

