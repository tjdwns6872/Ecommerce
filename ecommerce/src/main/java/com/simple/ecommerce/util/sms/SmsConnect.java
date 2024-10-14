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

    // properties에 있는 sms 수신 API URL을 url 변수에 저장
    @Value("${sms.api.url}")
    private String url;

    // properties에 있는 sms API KEY값을 apiKey 변수에 저장
    @Value("${sms.api.key}")
    private String apiKey;

    // properties에 있는 sms API SECRET값을 apiSecret 변수에 저장
    @Value("${sms.api.secret}")
    private String apiSecret;
        
    /**
     * SMS 수신
     * @param dto
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public String smsRequest(RequestSmsDto dto) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
        
        //url 값 targetUrl에 저장
        String targetUrl = url;
        //service에서 넘어온 DTO String값으로 저장
        String parameters = dto.toString();

        // 분석 필요
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().split("\\[")[0];
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));
        
        // 문자열 url URL형태로 변환
        URL url = new URL(targetUrl);
        // API통신을 위한 과정
        // API url 통신 준비
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // Authorization 헤더값 추가를 위해 문자열 생성
        String sha256Str = "HMAC-SHA256 ApiKey="+apiKey+", Date="+date+", salt="+salt+", signature="+signature;
        // 통신 방식 POST로 지정
        con.setRequestMethod("POST");
    
        // 헤더 값 지정
        con.setRequestProperty("Authorization", sha256Str);
        con.setRequestProperty("Content-Type", "application/json");
    
        // Body값을 넘기기 위해 true로 지정
        con.setDoOutput(true);
        
        // Body값을 입력하기 위한 객체 생성
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        // Body값 입력
        wr.writeBytes(parameters);
        // writeBytes를 통해 작성한 데이터를 전송
        wr.flush();
        // DataOutputStream 닫기
        wr.close();
    
        // 데이터 전송 후 응답코드 받기
        int responseCode = con.getResponseCode();
        // 응답 온 데이터 BufferedReader를 통해 저장
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        // 데이터 라인별로 분리 후 null값을 체크하기 위한 Temp변수 선언
        String line;
        // 데이터를 라인별로 저장하기 위한 객체 생성
        StringBuffer response = new StringBuffer();
        // 데이터를 라인별로 불러올 수 있을 때까지 반복
        while ((line = in.readLine()) != null) {
            // 라인별로 불러온 데이터 저장
            response.append(line);
        }
        //BufferedReader 닫기
        in.close();
    
        System.out.println("HTTP response code : " + responseCode);
        System.out.println("HTTP body : " + response.toString());
        return null;
    }
}
