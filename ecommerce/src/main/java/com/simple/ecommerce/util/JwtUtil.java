package com.simple.ecommerce.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.dto.jwt.UserJwtDto;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.*;

@Component
@Slf4j
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date getExpireDate(){
        Date now = new Date();
        return new Date(now.getTime()+jwtExpiration);
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
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public void validateToken(String token) {
        try {
            // 토큰을 각 섹션(Header, Payload, Signature)으로 분할
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();

            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));

            log.info("\n\n{}\n\n", payload);
        } catch (JwtException | IllegalArgumentException e) {
        }
    }
}
