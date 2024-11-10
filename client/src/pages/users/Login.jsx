import React, { useState } from 'react';
import '../../assets/css/commons.css';
import InputFactory from '../../components/input/InputFactory';
import ButtonFactory from '../../components/button/ButtonFactory';
import { SiKakaotalk, SiNaver, SiGoogle } from "react-icons/si";
import loginEvent from '../../assets/js/loginEvent';
function Login() {
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
                {ButtonFactory.basic("basic-login", "w-100", "로그인")}
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
