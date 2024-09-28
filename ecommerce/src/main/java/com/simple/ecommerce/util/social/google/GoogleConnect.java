package com.simple.ecommerce.util.social.google;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.security.SecureRandom;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.util.social.AbstractSocialConnect;
import com.simple.ecommerce.util.social.SocialConnect;

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
public class GoogleConnect extends AbstractSocialConnect{
    
    @Value("${google.api.client.id}")
    private String CLIENT_ID;

    @Value("${google.api.url}")
    private String GOOGLE_AUTH_URL;

    @Value("${google.api.callback.url}")
    private String REDIRECT_URL;

    @Value("${google.api.client.secret}")
    private String CLIENT_SECRET;

    @Override
    public String socialConnect() throws UnsupportedEncodingException {
        
        StringBuffer url = new StringBuffer();
        url.append(GOOGLE_AUTH_URL+"auth?");
        url.append("client_id="+CLIENT_ID);
        url.append("&response_type=code");
        url.append("&redirect_uri="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));
        url.append("&scope=email%20profile%20openid&access_type=offline");

        return url.toString();
    }

    @Override
    public String socialGetToken(SocialConnectDto socialConnectDto) {
        log.info("\n\n{}\n\n", socialConnectDto.toString());
        
        StringBuffer uriComponents = new StringBuffer();

        uriComponents.append("https://oauth2.googleapis.com/token?");
        uriComponents.append("client_id="+CLIENT_ID);
        uriComponents.append("&client_secret="+CLIENT_SECRET);
        uriComponents.append("&code="+socialConnectDto.getCode());
        uriComponents.append("&grant_type="+socialConnectDto.getGrantType());
        try {
            uriComponents.append("&redirect_uri="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL(uriComponents.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().close();
            // con.setRequestProperty("Content-Length", "1");

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

    @Override
    public String socialUserByToken(SocialTokenDto socialTokenDto) {
        return null;
    }

    
}
