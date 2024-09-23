package com.simple.ecommerce.util;

import com.liferay.portal.kernel.security.SecureRandom;
import com.simple.ecommerce.dto.SocialConnectDto;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NaverConnect extends SocialConnect{
    
    @Value("${naver.api.client.id}")
    private String CLIENT_ID;

    @Value("${naver.api.url}")
    private String NAVER_AUTH_URL;

    @Value("${naver.api.callback.url}")
    private String REDIRECT_URL;

    @Value("${naver.api.client.secret}")
    private String CLIENT_SECRET;

    @Override
    public String socialConnect() throws UnsupportedEncodingException {
        
        StringBuffer url = new StringBuffer();
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString(32);

        url.append(NAVER_AUTH_URL+"authorize?");
        url.append("client_id="+CLIENT_ID);
        url.append("&response_type=code");
        url.append("&redirect_url="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));
        url.append("&state="+URLEncoder.encode(state, "UTF-8"));

        return url.toString();
    }

    @Override
    public String socialGetToken(SocialConnectDto socialConnectDto) {
        log.info("\n\nstate=[{}]\n\ncode=[{}]", socialConnectDto.getState(), socialConnectDto.getCode());

        StringBuffer uriComponents = new StringBuffer();
        
        uriComponents.append(NAVER_AUTH_URL+"token?");
        uriComponents.append("grant_type="+socialConnectDto.getGrantType());
        uriComponents.append("&client_id="+CLIENT_ID);
        uriComponents.append("&client_secret="+CLIENT_SECRET);
        uriComponents.append("&code="+socialConnectDto.getCode());
        uriComponents.append("&state="+socialConnectDto.getState());

        try {
            URL url = new URL(uriComponents.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

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

        return null;
    }
    
}
