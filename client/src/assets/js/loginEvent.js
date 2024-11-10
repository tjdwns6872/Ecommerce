import ApiFactory from '../../services/ApiFactory';

const loginEvent = {
    socialLogin: async (type) => {
        try {
            const data = await ApiFactory.post("http://localhost:8081/ecommerce/api/user/login/"+type);
            console.log('API 호출 성공:', data); // 성공적으로 받은 데이터 처리
          } catch (error) {
            console.error('API 호출 실패:', error); // 에러 처리
          }
    }
}

export default loginEvent;