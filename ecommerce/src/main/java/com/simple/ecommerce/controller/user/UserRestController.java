package com.simple.ecommerce.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.sms.CustomSmsDto;
import com.simple.ecommerce.dto.sms.MessageDataDto;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.users.UsersDataResultDto;
import com.simple.ecommerce.dto.users.UsersFindDto;
import com.simple.ecommerce.dto.users.UsersJoinDto;
import com.simple.ecommerce.dto.users.UsersJoinResultDto;
import com.simple.ecommerce.dto.users.UsersLoginDto;
import com.simple.ecommerce.service.sms.SmsService;
import com.simple.ecommerce.service.user.UsersDataService;
import com.simple.ecommerce.service.user.UsersJoinService;
import com.simple.ecommerce.service.user.UsersLoginService;
import com.simple.ecommerce.util.AjaxResult;
import com.simple.ecommerce.util.JsonUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/ecommerce/api/user")
public class UserRestController {
    
    @Autowired
    private UsersLoginService usersLoginService;

    @Autowired
    private UsersJoinService joinService;

    @Autowired
    private UsersDataService dataService;

    @Autowired
    private SmsService smsService;

    //회원가입 시 사용되는 SMS 인증 컨트롤러
    @PutMapping("/{certType}/CertCode")
    public ResponseEntity<AjaxResult<Void>> joinCertCode(@RequestBody String phone, @PathVariable String certType) throws Exception{
        // json형태의 문자열로 넘어온 데이터 json형태로 변경
        JSONObject json = JsonUtil.stringToJson(phone);
        // json에서 전화번호 데이터 추출
        phone = (String) JsonUtil.getJsonValue(json, "phone");
        // dto 생성
        RequestSmsDto smsDto = new RequestSmsDto();
        // message dto 생성
        MessageDataDto messageDataDto = new MessageDataDto();
        // 추출한 전화번호 데이터 삽입
        messageDataDto.setTo(phone);
        // message dto 삽입
        smsDto.setMessage(messageDataDto);
        // 기능 데이터 처리시 사용되는 sms 타입 선언
        CustomSmsDto customSmsDto = new CustomSmsDto();
        customSmsDto.setCustomType(certType);
        smsDto.setCustom(customSmsDto);;
        // SMS 전송
        smsService.smsRequest(smsDto);

        AjaxResult<Void> response = AjaxResult.<Void>builder()
            .status(HttpStatus.OK.value())
            .message("SMS 전송")
            .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }


    //회원가입 컨트롤러
    @PutMapping("/join")
    public ResponseEntity<AjaxResult<UsersJoinResultDto>> join(@RequestBody UsersJoinDto joinDto) {
        //회원가입 시도
        UsersJoinResultDto result = joinService.join(joinDto);
        //회원가입 성공하면 AjaxResult 데이터 폼에 맞춰 데이터 삽입
        AjaxResult<UsersJoinResultDto> response = AjaxResult.<UsersJoinResultDto>builder()
            //HttpStatus Created 상태 코드 삽입(201) 
            .status(HttpStatus.CREATED.value())
            //API 사용자한테 안내될 메시지
            .message("회원가입에 성공했습니다.")
            //회원가입 성공 후 service에서 받아온 데이터 삽입
            .data(result)
            // 빌드
            .build();
        
        // ResponseEntity 폼에 맞춰 데이터 입력
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //로그인 컨트롤러
    @PostMapping("/login")
    public ResponseEntity<AjaxResult<String>> login(@RequestBody UsersLoginDto loginDto) throws IOException {
        // 로그인 시도
        String data = usersLoginService.login(loginDto);
        AjaxResult<String> response = AjaxResult.<String>builder()
            .status(HttpStatus.OK.value())
            .message("로그인 성공")
            .data(data)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //소셜 로그인 컨트롤러
    @PostMapping("/login/{platform}")
    public ResponseEntity<AjaxResult<String>> login(@PathVariable("platform") String platform) throws IOException {
        //URL에서 받은 PathVariable값을 가지고 login 함수로 이동
        String url = usersLoginService.login(platform);
        AjaxResult<String> response = AjaxResult.<String>builder()
            .status(HttpStatus.OK.value())
            .message("플랫폼 로그인 링크 발급")
            .data(url)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //소셜로그인 시 인증코드를 받는 컨트롤러
    // @GetMapping("/{platform}/callback")
    // public ResponseEntity<AjaxResult<String>> platformCallback(SocialConnectDto socialConnectDto
    // , @PathVariable("platform") String platform) throws JsonMappingException, JsonProcessingException {
    //     String token = usersLoginService.socialCallback(socialConnectDto, platform);
    //     AjaxResult<String> response = AjaxResult.<String>builder()
    //         .status(HttpStatus.OK.value())
    //         .message("로그인 성공")
    //         .data(token)
    //         .build();
    //     return ResponseEntity.status(response.getStatus()).body(response);
    // }

    //소셜로그인 시 인증코드를 받는 컨트롤러
    @GetMapping("/callback/{platform}")
    public void platformCallback(SocialConnectDto socialConnectDto
    , @PathVariable("platform") String platform
    , HttpServletResponse respons) throws IOException {
        String token = usersLoginService.socialCallback(socialConnectDto, platform);
        try {
            String script = "<script>"
                + "window.opener.postMessage({ token: '" + token + "' }, 'http://localhost:3000');"
                + "window.close();"
                + "</script>";
            respons.setContentType("text/html;charset=UTF-8");
            respons.getWriter().write(script);
        }catch(Exception e){
            String errorScript = "<script>"
            + "window.opener.postMessage({ error: '로그인 실패: " + e.getMessage() + "' }, 'http://localhost:3000');"
            + "window.close();"
            + "</script>";

            respons.setContentType("text/html;charset=UTF-8");
            respons.getWriter().write(errorScript);
        }
    }

    //회원 정보를 찾을 때 사용되는 컨트롤러
    @GetMapping("/find")
    public ResponseEntity<AjaxResult<String>> find(UsersFindDto usersFindDto) throws Exception{
        String result = dataService.usersData(usersFindDto);
        AjaxResult<String> response = AjaxResult.<String>builder()
            .status(HttpStatus.OK.value())
            .message("사용자 확인")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //회원 상세데이터(테스트용)
    @GetMapping("/detail")
    public ResponseEntity<AjaxResult<UsersDataResultDto>> detail(@RequestParam Integer id){
        UsersDataResultDto dto = dataService.usersData(id);
        AjaxResult<UsersDataResultDto> response = AjaxResult.<UsersDataResultDto>builder()
            //HttpStatus Ok 상태 코드 삽입(200) 
            .status(HttpStatus.OK.value())
            //API 사용자한테 안내될 메시지
            .message("사용자 확인")
            //조회 성공 후 service에서 받아온 데이터 삽입
            .data(dto)
            // 빌드
            .build();
        
        // ResponseEntity 폼에 맞춰 데이터 입력
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
