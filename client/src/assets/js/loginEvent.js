import ApiFactory from '../../services/ApiFactory';
import { useState, useEffect, useCallback } from 'react';

const loginEvent = {
  socialLogin: async (type) => {
    var popup = null;
    try {
      var data = await ApiFactory.post("http://localhost:8081/ecommerce/api/user/login/"+type);
      console.log('API 호출 성공:', data); // 성공적으로 받은 데이터 처리
      popup = window.open(data.data, 'SocialLogin', 'width=700, height=600, top=50, left=50');
      console.log(popup.postMessage);
    } catch (error) {
      console.error('API 호출 실패:', error); // 에러 처리
    }
  }
}

export default loginEvent;