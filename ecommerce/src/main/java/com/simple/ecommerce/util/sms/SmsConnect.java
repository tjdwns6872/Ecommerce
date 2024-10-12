package com.simple.ecommerce.util.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.dto.sms.RequestSmsDto;


@Component
public class SmsConnect {

    @Value("${sms.api.url}")
    private String url;

    @Value("${sms.api.key}")
    private String apiKey;
        
    public String smsRequest(RequestSmsDto dto) throws IOException{
        String targetUrl = url;
        String parameters = dto.toString();
    
        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
    
        con.setRequestMethod("POST");
    
        con.setRequestProperty("Authorization", "HMAC-SHA256 apiKey="+apiKey+", date=2019-07-01T00:41:48Z, salt=jqsba2jxjnrjor, signature=1779eac71a24cbeeadfa7263cb84b7ea0af1714f5c0270aa30ffd34600e363b4");
        con.setRequestProperty("Content-Type", "application/json");
    
        con.setDoOutput(true);
        
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();
    
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = in.readLine()) != null) {
          response.append(line);
        }
        in.close();
    
        System.out.println("HTTP response code : " + responseCode);
        System.out.println("HTTP body : " + response.toString());
        return null;
    }
}
