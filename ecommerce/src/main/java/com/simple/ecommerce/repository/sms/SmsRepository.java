package com.simple.ecommerce.repository.sms;

import org.springframework.stereotype.Repository;

import com.simple.ecommerce.entity.sms.SmsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Integer>{
    <S extends SmsEntity> S save(S entity);

    //인증코드를 고유 식별 번호를 통해 필터 후 불러오는 코드
    SmsEntity findByEcCertId(Integer ecCertId);
}
