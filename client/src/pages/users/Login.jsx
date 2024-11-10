import React, { useState } from 'react';
import ApiFactory from '../../services/ApiFactory';
import '../../assets/css/commons.css';
import InputFactory from '../../components/input/InputFactory';
import ButtonFactory from '../../components/button/ButtonFactory';
import { SiKakaotalk, SiNaver, SiGoogle } from "react-icons/si";
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
                {ButtonFactory.basic("login-btn", "w-100", "로그인")}
            </div>
            <div className='w-100' style={{display: "flex", justifyContent: "space-evenly"}}>
                {ButtonFactory.icon("naver", "", SiNaver)}
                {ButtonFactory.icon("kakao", "", SiKakaotalk)}
                {ButtonFactory.icon("google", "", SiGoogle)}
            </div>
        </div>
    );
}

export default Login;
