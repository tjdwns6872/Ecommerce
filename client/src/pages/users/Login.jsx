import React, { useState, useEffect } from 'react';
import '../../assets/css/commons.css';
import InputFactory from '../../components/input/InputFactory';
import ButtonFactory from '../../components/button/ButtonFactory';
import { SiKakaotalk, SiNaver, SiGoogle } from "react-icons/si";
import loginEvent from '../../assets/js/user/loginEvent';
function Login() {

    const [formData, setFormData] = useState({
        userId: '',
        userPw: ''
    });

    const handleValueChange = (id, value) => {
        setFormData(prevData => ({
          ...prevData,
          [id]: value
        }));
    };

    useEffect(() => {
        const handleMessage = (event) => {
            // 안전을 위해 origin 검증
            if (event.origin !== "http://localhost:8081") return;
            const { token } = event.data;
            if (token) {
                localStorage.setItem('accessToken', token);
                window.location.href='/home';
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
                {InputFactory.basic("userId", "아이디를 입력하세요.", formData.userId, 'input-field', false, handleValueChange)}
                {InputFactory.password("userPw", "비밀번호를 입력하세요.")}
            </div>
            <div className='w-100'>
                {ButtonFactory.basic("basic-login", "로그인", () => loginEvent.basicLogin(), "w-100")}
            </div>
            <div className='w-100' style={{display: "flex", justifyContent: "space-evenly"}}>
                <a href='/signup'>회원가입</a>
                <a href='/find'>아이디/비밀번호찾기</a>
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
