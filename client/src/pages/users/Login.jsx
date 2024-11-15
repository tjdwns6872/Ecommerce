import React, { useEffect } from 'react';
import '../../assets/css/commons.css';
import InputFactory from '../../components/input/InputFactory';
import ButtonFactory from '../../components/button/ButtonFactory';
import { SiKakaotalk, SiNaver, SiGoogle } from "react-icons/si";
import loginEvent from '../../assets/js/loginEvent';
function Login() {

    useEffect(() => {
        const handleMessage = (event) => {
            // 안전을 위해 origin 검증
            if (event.origin !== "http://localhost:8081") return;
            const { token } = event.data;
            if (token) {
                console.log("토큰 수신 완료:", token);
                // 토큰 저장 후 추가 작업 (예: 로그인 상태 업데이트)
            }
        };

        window.addEventListener("message", handleMessage);

        return () => {
            window.removeEventListener("message", handleMessage);
        };
    }, []);

    return (
        <div className="login-container">
            <div className="login-box">
                <h2>로그인</h2>
            </div>
            <div className='w-100'>
                {InputFactory.basic("user-id", "아이디를 입력하세요.", "", "")}
                {InputFactory.password("user-pw")}
            </div>
            <div className='w-100'>
                {ButtonFactory.basic("basic-login", "로그인", () => loginEvent.basicLogin(), "w-100")}
            </div>
            <div className='w-100' style={{display: "flex", justifyContent: "space-evenly"}}>
                {ButtonFactory.icon("social-login", SiNaver, () => loginEvent.socialLogin("naver"))}
                {ButtonFactory.icon("social-login", SiKakaotalk, () => loginEvent.socialLogin("kakao"))}
                {ButtonFactory.icon("social-login", SiGoogle, () => loginEvent.socialLogin("google"))}
            </div>
        </div>
    );
}

export default Login;
