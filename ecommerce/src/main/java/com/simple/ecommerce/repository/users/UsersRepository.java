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

    /**
     * 이메일과 전화번호 기준 회원 조회
     * @param email - (String) 사용자한테 입력 받은 이메일
     * @param phone - (String) 사용자한테 입력 받은 전화번호
     * @return UsersEntity
    */
    UsersEntity findByEcUsersEmailAndEcUsersPhone(String email, String phone);

    /**
     * 이름과 전화번호 기준 회원 조회
     * @param phone - (String) 사용자한테 입력 받은 전화번호
     * @param name - (String) 사용자한테 입력 받은 이름
     * @return UsersEntity
    */
    UsersEntity findByEcUsersPhoneAndEcUsersName(String phone, String name);

    /**
     * 회원 데이터 저장
     * @param entity - (UsersEntity) 사용자한테 입력 데이터
     */
    <S extends UsersEntity> S save(S entity);

    /**
     * 고유 식별 번호 기준 회원 조회
     * @param id - (Integer) 회원 고유 식별 번호
     * @return UsersEntity
     */
    UsersEntity findByEcUsersId(Integer id);

}
