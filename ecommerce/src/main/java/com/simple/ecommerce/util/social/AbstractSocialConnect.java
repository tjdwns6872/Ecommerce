package com.simple.ecommerce.util.social;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

public abstract class AbstractSocialConnect implements SocialConnect {
    // 공통 로직 구현
    public abstract String socialConnect() throws UnsupportedEncodingException;

    public abstract String socialGetTokenUrl(SocialConnectDto socialConnectDto);

    public abstract String socialUserByToken(SocialTokenDto socialTokenDto );

    public String socialGetToken(String uriComponents){
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

            br.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}