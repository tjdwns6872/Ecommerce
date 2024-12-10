import ApiFactory from '../../../services/ApiFactory';

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
      var id = document.getElementById("userId").value;
      var pw = document.getElementById("userPw").value;
      var data = await ApiFactory.post("http://localhost:8081/ecommerce/api/user/login", {"ecUsersEmail":id,"ecUsersPassword":pw});
      if(data.status === 200){
        localStorage.setItem('accessToken', data.data);
        window.location.href="/"
      }
    } catch(error){
      //토스트 메시지 컴포넌트 추가 후 수정 예정
      console.error(error.response.data.message);
    }
  }
}

export default loginEvent;