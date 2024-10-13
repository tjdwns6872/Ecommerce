package com.simple.ecommerce.util.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.dto.sms.RequestSmsDto;


@Component
public class SmsConnect {

    @Value("${sms.api.url}")
    private String url;

    @Value("${sms.api.key}")
    private String apiKey;

    @Value("${sms.api.secret}")
    private String apiSecret;
        
    public String smsRequest(RequestSmsDto dto) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
        String targetUrl = url;
        String parameters = dto.toString();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().split("\\[")[0];

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));
        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String sha256Str = "HMAC-SHA256 ApiKey="+apiKey+", Date="+date+", salt="+salt+", signature="+signature;
        con.setRequestMethod("POST");
    
        con.setRequestProperty("Authorization", sha256Str);
        con.setRequestProperty("Content-Type", "application/json");
    
        con.setDoOutput(true);
        
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("{\"message\":{\"to\":\"01075579897\",\"from\":\"01075579897\",\"text\":\"내용\"}}\"");
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
