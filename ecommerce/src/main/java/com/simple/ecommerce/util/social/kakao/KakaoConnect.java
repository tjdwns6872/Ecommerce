package com.simple.ecommerce.util.social.kakao;

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
public class KakaoConnect extends AbstractSocialConnect{
    
    @Value("${kakao.api.client.id}")
    private String CLIENT_ID;

    @Value("${kakao.api.url}")
    private String KAKAO_AUTH_URL;

    @Value("${kakao.api.callback.url}")
    private String REDIRECT_URL;

    @Override
    public String socialConnect() throws UnsupportedEncodingException {
        
        StringBuffer url = new StringBuffer();
        log.info(REDIRECT_URL);
        url.append(KAKAO_AUTH_URL+"authorize?");
        url.append("client_id="+CLIENT_ID);
        url.append("&response_type=code");
        url.append("&redirect_uri="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));

        return url.toString();
    }

    @Override
    public String socialGetToken(SocialConnectDto socialConnectDto) {
        StringBuffer uriComponents = new StringBuffer();
        
        uriComponents.append(KAKAO_AUTH_URL+"token?");
        uriComponents.append("grant_type="+socialConnectDto.getGrantType());
        uriComponents.append("&client_id="+CLIENT_ID);
        uriComponents.append("&redirect_uri");
        uriComponents.append("&code="+socialConnectDto.getCode());

        try {
            URL url = new URL(uriComponents.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            SocialTokenDto dto = new SocialTokenDto();

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

    @Override
    public String socialUserByToken(SocialTokenDto socialTokenDto) {
        try {
            String accessToken = socialTokenDto.getAccessToken();
            String tokenType = socialTokenDto.getTokenType();

            URL url = new URL("https://openapi.naver.com/v1/nid/me");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", tokenType + " " + accessToken);

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
