package com.simple.ecommerce.util.social;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSocialConnect implements SocialConnect {
    
    // 소셜 로그인 플랫폼 다양화를 위한 커넥션 추상메소드
    public abstract String socialConnect() throws UnsupportedEncodingException;

    // 플랫폼 다양화를 위한 추상메소드로 인증코드로 토큰 발급을 위한 URL 생성 메소드
    public abstract String socialGetTokenUrl(SocialConnectDto socialConnectDto);

    /**
     * 토큰 발급이후 DTO로 객체화
     * @param uriComponents - (String)생성한 토큰 발급 URL
     * @return 토큰 값 리턴
     * @throws 어떤 상황에서 예외가 발생!
     */
    public SocialTokenDto socialGetToken(String uriComponents){

        // 토큰 발급 시 타입이 JSON로 오는데 그걸 DTO로 변경하기 위한 인스턴스 생성
        ObjectMapper mapper = new ObjectMapper();

        try {
            // 입력 받은 String 타입에 URL URL타입으로 변경
            URL url = new URL(uriComponents.toString());
            // 토큰 발급 API호출을 위해 URL 커넥션 생성
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            // 토큰 발급 API Method 타입 POST로 지정
            con.setRequestMethod("POST");

            // 411 Error로 인하여 해당 소스 추가
            con.setDoOutput(true);
            con.getOutputStream().close();

            // API호출 후 리턴된 상태코드를 int형 변수에 삽입
            int responseCode = con.getResponseCode();
            // API호출해서 받은 리턴 값들을 저장하기 위한 BufferedReader
            BufferedReader br;

            // 상태코드가 200일 때 -> 호출 성공 케이스
            if(responseCode==200) { // 정상 호출
                // API호출 후 받은 리턴 값 저장
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // 상태코드가 200이 아닐 때 -> 호출 실패 케이스
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            // 저장한 리턴 값을 변환하기 위해 String 타입 선언
            String inputLine;
            // 저장한 리턴 값을 한 줄씩 저장하기 위해 StringBuffer 생성
            StringBuffer response = new StringBuffer();
            // 저장한 리턴 값을 한 줄씩 읽고, 만약 읽은 값이 null이면 반복문 종료
            while ((inputLine = br.readLine()) != null) {
                // 한 줄씩 읽은 String 값을 저장
                response.append(inputLine);
            }
            // 저장한 문자열을 SocialTokenDto타입으로 변환
            SocialTokenDto socialTokenDto = mapper.readValue(response.toString(), SocialTokenDto.class);

            br.close();
            // 토큰값 리턴
            return socialTokenDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 유저 데이터 불러오는 메소드
     * @param uriComponents - 유저 데이터를 요청하는 URL
     * @param socialTokenDto - 유저 데이터 요청할 때 쓰이는 토큰
     * @return 토큰 값 리턴
     * @throws 어떤 상황에서 예외가 발생!
     */
    public String socialGetUserData(SocialTokenDto socialTokenDto, String uriComponents){

        // 플랫폼에서 발급 받은 Access토큰 값
        String accessToken = socialTokenDto.getAccessToken();
        // 토큰 타입
        String tokenType = socialTokenDto.getTokenType();
        
        try {
            // 유저 데이터 발급 String 타입에 URL URL타입으로 변경
            URL url = new URL(uriComponents.toString());
            // 유저 데이터 발급 API호출을 위해 URL 커넥션 생성
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            // 유저 데이터 발급 API Method 타입 GET로 지정
            con.setRequestMethod("GET");
            // 헤더 설정
            con.setRequestProperty("Authorization", tokenType + " " + accessToken);
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // API호출 후 리턴된 상태코드를 int형 변수에 삽입
            int responseCode = con.getResponseCode();
            // API호출해서 받은 리턴 값들을 저장하기 위한 BufferedReader
            BufferedReader br;
            // 상태코드가 200일 때 -> 호출 성공 케이스
            if(responseCode==200) { // 정상 호출
                // API호출 후 받은 리턴 값 저장
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // 상태코드가 200이 아닐 때 -> 호출 실패 케이스
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            // 저장한 리턴 값을 변환하기 위해 String 타입 선언
            String inputLine;
            // 저장한 리턴 값을 한 줄씩 저장하기 위해 StringBuffer 생성
            StringBuffer response = new StringBuffer();
            // 저장한 리턴 값을 한 줄씩 읽고, 만약 읽은 값이 null이면 반복문 종료
            while ((inputLine = br.readLine()) != null) {
                // 한 줄씩 읽은 String 값을 저장
                response.append(inputLine);
            }
            br.close();
            // 유저 데이터 리턴
            return response.toString();
        }catch(MalformedURLException e){ 
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tokenType;
    }
}