package com.simple.ecommerce.repository.sms;

import org.springframework.stereotype.Repository;

import com.simple.ecommerce.entity.sms.SmsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Integer>{
    <S extends SmsEntity> S save(S entity);
}
