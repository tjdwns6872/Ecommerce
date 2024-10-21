package com.simple.ecommerce.serviceimpl.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.sms.RequestCertDto;
import com.simple.ecommerce.entity.sms.SmsEntity;
import com.simple.ecommerce.exception.sms.codeErrorException;
import com.simple.ecommerce.repository.sms.SmsRepository;
import com.simple.ecommerce.service.sms.SmsCertCodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsCertCodeServiceImpl implements SmsCertCodeService{
    
    @Autowired
    private SmsRepository smsRepository;

    /**
     * 인증번호 확인 및 비교해주는 메소드
     * @param certDto
     */
    @Override
    public void codeCert(RequestCertDto certDto) {
        // 사용자한테 받은 인증코드와 데이터베이스에 저장된 인증코드를 비교 후 결과를 저장하기 위한 변수
        boolean result = false;
        try {
            // DB에서 사용자 요청에 맞는 인증코드를 불러오는 코드
            SmsEntity entity = smsRepository.findByEcCertId(certDto.getCertId());
            // DB인증코드와 사용자한테 받은 인증코드를 비교
            result = entity.getEcCertCode().equals(certDto.getCode());
            // 비교 후 값이 다를 경우 예외처리
            if(!result){
                throw new NullPointerException();
            }
        } catch(NullPointerException e){
            throw new codeErrorException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
