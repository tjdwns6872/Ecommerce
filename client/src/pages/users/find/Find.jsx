import React, { useState, useEffect } from "react";
import FindCode from './FindCode';
import { Link } from 'react-router-dom'; // 로그인 링크를 위한 react-router-dom 사용

function Find(){

    return (
        <div className="signup-container">
            <div className="signup-header">
                <h2>아이디/비밀번호 찾기</h2>
                    <FindCode/>
            </div>
            <div className="signup-footer">
                <p>이미 계정이 있으신가요? <Link to="/login">로그인</Link></p>
            </div>
        </div>
    )
}

export default Find;