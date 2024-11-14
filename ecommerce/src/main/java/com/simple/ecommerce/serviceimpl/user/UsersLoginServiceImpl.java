package com.simple.ecommerce.serviceimpl.user;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simple.ecommerce.component.social.SocialConnectFactory;
import com.simple.ecommerce.dto.jwt.UserJwtDto;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.dto.social.SocialUserDto;
import com.simple.ecommerce.dto.users.UsersLoginDto;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.exception.users.LoginException;
import com.simple.ecommerce.exception.users.SocialLoginException;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.service.user.UsersLoginService;
import com.simple.ecommerce.util.JwtUtil;
import com.simple.ecommerce.util.ShaUtil;
import com.simple.ecommerce.util.social.SocialConnect;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersLoginServiceImpl implements UsersLoginService{
    
    // 여러 플랫폼의 로그인 API를 사용하기 위한 커넥션 컴포넌트
    @Autowired
    private SocialConnectFactory socialConnectFactory;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 일반회원 로그인 인터페이스
     * @param UsersLoginDto - UsersLoginDto
     * @return JWT 토큰값
     * @throws JsonProcessingException 
     * @throws LoginException
   */
    @Override
    public String login(UsersLoginDto dto) throws JsonProcessingException {
        UserJwtDto jwtDto = null;
        // 로그인 시도를 위해 사용자한테 받은 이메일로 DB 조회
        UsersEntity entity = usersRepository.findByEcUsersEmail(dto.getEcUsersEmail());
        try{
            if(!ShaUtil.sha256Encode(dto.getEcUsersPassword()).equals(entity.getEcUsersPassword())){
                throw new LoginException();
            }
            jwtDto = UserJwtDto.builder()
                .ecUsersId(entity.getEcUsersId())
                .ecUsersEmail(entity.getEcUsersEmail())
                .ecUsersName(entity.getEcUsersName())
                .build();
        }catch(Exception e){
            throw new LoginException();
        }
        return jwtUtil.createToken(jwtDto);
    }
    
    /**
     * 소셜로그인 메소드
     * @param platform - (String)사용자가 요청한 플랫폼 명
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
   */
    @Override
    public String login(String platform) {
        try {
            //socialConnectFactory를 통해 사용자가 요청한 플랫폼 커넥션을 생성
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            // API 접근을 위한 URL 생성 후 리턴
            return socialConnect.socialConnect();
        } catch (UnsupportedEncodingException e) {
            log.error("Error during social login", e);
            // throw new SocialLoginException("Error during social login", e);
        }
        return null;
    }


    /**
     * 인증 후 리다이렉트된 매소드로 토큰 URL를 생성, 토큰 발급 후 유저 데이터를 처리하는 메소드 
     * @param socialConnectDto - (SocialConnectDto) 토큰 값을 요청할 때 사용할 파라미터 값
     * @param platform - (String) 사용자가 요청한 플랫폼 명
     * @return JWT 토큰 값
     * @throws JsonProcessingException 
     * @throws 어떤 상황에서 예외가 발생!
   */
    @Override
    public String socialCallback(SocialConnectDto socialConnectDto, String platform) throws JsonProcessingException {
        UsersEntity entity = new UsersEntity();
        try {
            // API에서 토큰 발급을 위한 기본 grant_type값 삽입
            socialConnectDto.setGrantType("authorization_code");
            // 유저가 요청한 플랫폼 커넥션 생성
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            // 발급 받은 인증코드를 기반으로 토큰 발급 URL 생성
            String url = socialConnect.socialGetTokenUrl(socialConnectDto);
            // 생성된 토큰 발급 URL로 토큰 발급
            SocialTokenDto socialTokenDto = socialConnect.socialGetToken(url);
            // 유저 데이터 URL 생성
            String userUrl = socialConnect.socialGetUrl();
            // 유저 데이터 가져오기
            String data = socialConnect.socialGetUserData(socialTokenDto, userUrl);
            // 유저 데이터(문자열) SocialUserDto로 변환
            SocialUserDto dto = socialConnect.UserDataToDto(data);
            // 해당 유저 데이터가 DB에 있는지 확인
            entity = usersRepository.findByEcUsersEmail(dto.getEmail());
            log.info("Users Login Entity Null Check====>{}", entity.toString());
        } catch(NullPointerException e){
            throw new SocialLoginException();
        }catch(Exception e){
            e.printStackTrace();
        }
        //JWT 토큰 생성 시 포함할 유저 데이터 저장
        UserJwtDto jwtDto = UserJwtDto.builder()
            .ecUsersId(entity.getEcUsersId())
            .ecUsersEmail(entity.getEcUsersEmail())
            .ecUsersName(entity.getEcUsersName())
            .build();
        //JWT 토큰 생성 후 리턴 
        return jwtUtil.createToken(jwtDto);
    }

    @Override
    public String socialTokenRefresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'socialTokenRefresh'");
    }
    
}
