package com.simple.ecommerce.util.social;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSocialConnect implements SocialConnect {
    
    // 공통 로직 구현
    public abstract String socialConnect() throws UnsupportedEncodingException;

    public abstract String socialGetTokenUrl(SocialConnectDto socialConnectDto);

    public SocialTokenDto socialGetToken(String uriComponents){

        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = new URL(uriComponents.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            con.setRequestMethod("POST");

            // 411 Error로 인하여 해당 소스 추가
            con.setDoOutput(true);
            con.getOutputStream().close();

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            SocialTokenDto socialTokenDto = mapper.readValue(response.toString(), SocialTokenDto.class);

            br.close();
            return socialTokenDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String socialGetUserData(SocialTokenDto socialTokenDto, String uriComponents){

        String accessToken = socialTokenDto.getAccessToken();
        String tokenType = socialTokenDto.getTokenType();
        
        try {
            URL url = new URL(uriComponents);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", tokenType + " " + accessToken);
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenType;
    }
}