import ApiFactory from '../../services/ApiFactory';
import { useState, useEffect, useCallback } from 'react';

const loginEvent = {
  socialLogin: async (type) => {
    let popup = null;
    try {
      const response = await ApiFactory.post('http://localhost:8081/ecommerce/api/user/login/'+type);
      const socialLoginUrl = response.data; // 서버에서 받은 로그인 URL
      // 팝업 열기
      popup = window.open(socialLoginUrl, 'SocialLogin', 'width=700,height=600,top=50,left=50');

      // 팝업 상태 확인 (닫힘 감지)
      const timer = setInterval(() => {
        if (popup && popup.closed) {
          clearInterval(timer);
        }
      }, 500);
    } catch (error) {
      //회원가입 화면으로 넘어갈 예정
    }
  }, basicLogin: async () => {
    try{
      var id = document.getElementById("user-id").value;
      var pw = document.getElementById("user-pw").value;
      var data = await ApiFactory.post("http://localhost:8081/ecommerce/api/user/login", {"ecUsersEmail":id,"ecUsersPassword":pw});
      console.log('API 호출 성공:', data); // 성공적으로 받은 데이터 처리
    } catch(error){
      //토스트 메시지 컴포넌트 추가 후 수정 예정
      console.error(error.response.data.message);
    }
  }
}

export default loginEvent;