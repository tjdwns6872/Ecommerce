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
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date getExpireDate(){
        ZonedDateTime kstDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        kstDateTime = kstDateTime.plusSeconds(jwtExpiration);
        return Date.from(kstDateTime.toInstant());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getClaims(UserJwtDto jwtDto) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(jwtDto, HashMap.class); 
    }
    
    public String createToken(UserJwtDto jwtDto) throws JsonProcessingException {
        Key key = getSigningKey();
        Date expireDate = getExpireDate();
        Map<String, Object> claims = getClaims(jwtDto);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("admin")
                .setClaims(claims)
                .setExpiration(expireDate)
                .setId(String.valueOf(jwtDto.getEcUsersId()))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("{}", e);
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

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
