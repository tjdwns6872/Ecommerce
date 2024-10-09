package com.simple.ecommerce.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple.ecommerce.entity.users.UsersEntity;

//회원 Data JPA Repository 
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer>{

    /**
     * 이메일 기준 회원 조회
     * @param email - (String) 사용자한테 입력 받은 이메일
     * @return UsersEntity
    */
    UsersEntity findByEcUsersEmail(String email);

    <S extends UsersEntity> S save(S entity);

}
