package com.simple.ecommerce.entity.sms;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "EC_CERT")
public class SmsEntity {
    
    //인증코드 고유식별자
    @Id
    @Column(name = "EC_CERT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ecCertId;

    //인증코드를 받은 전화번호
    @Column(name = "EC_CERT_PHONE")
    private String ecCertPhone;
    
    //인증코드
    @Column(name = "EC_CERT_CODE")
    private String ecCertCode;

    //인증코드 발급일
    @Column(name = "EC_CERT_CREATED_AT")
    private Date ecCertCreatedAt;

    //인증코드 삭제여부
    @Column(name = "EC_CERT_DE_FLG")
    private boolean ecCertDeFlg;

    //인증코드 인증여부
    @Column(name = "EC_CERT_CHECK")
    private boolean ecCertCheck;
}
