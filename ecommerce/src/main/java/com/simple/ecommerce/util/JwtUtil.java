package com.simple.ecommerce.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.dto.jwt.UserJwtDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import kotlinx.datetime.Instant;
import kotlinx.datetime.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.security.Key;
import java.util.*;

@Component
@Slf4j
public class JwtUtil {
    
    //JWT 암호키
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    //토큰 유효기간
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    /**
     * 암호키를 base64로 디코딩 후 리턴
     * @return Key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 현재 KST에 토큰 값의 유효기간을 더해서 리턴
     * @return Date
     */
    private Date getExpireDate(){
        //현재 KST 시간을 구하는 코드
        ZonedDateTime kstDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        //현재 시간에서 토큰값에 유효기간을 더하는 코드
        kstDateTime = kstDateTime.plusSeconds(jwtExpiration);
        return Date.from(kstDateTime.toInstant());
    }

    /**
     * 넘겨받은 유저 데이터를 Map으로 변환해주는 메소드
     * @param jwtDto
     * @return Map
     * @throws JsonProcessingException
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getClaims(UserJwtDto jwtDto) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(jwtDto, HashMap.class); 
    }
    
    /**
     * JWT 토큰 생성한 메소드
     * @param jwtDto
     * @return String
     * @throws JsonProcessingException
     */
    public String createToken(UserJwtDto jwtDto) throws JsonProcessingException {
        //Key값
        Key key = getSigningKey();
        //유효기간
        Date expireDate = getExpireDate();
        //유저데이터
        Map<String, Object> claims = getClaims(jwtDto);

        //JWT에 저장할 데이터 세팅 후 리턴
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("admin")
                .setClaims(claims)
                .setExpiration(expireDate)
                .setId(String.valueOf(jwtDto.getEcUsersId()))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    /**
     * JWT 토큰값이 유효한지 확인하는 메소드
     * @param token
     * @return boolean
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("{}", e);
            return false;
        }
    }

    /**
     * 헤더에 있는 토큰값 구하는 메소드
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request) {
        //Authorization에 저장된 토큰값 저장
        String bearerToken = request.getHeader("Authorization");
        //토큰값 앞에 Bearer 문자열이 있을 경우 삭제 후 리턴
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * 분석필요
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                              .setSigningKey(getSigningKey())
                              .build()
                              .parseClaimsJws(token)
                              .getBody();
        
        User principal = new User(claims.getId(), "", List.of());
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }
}
