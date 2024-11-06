import React, { useState } from 'react';
import '../../assets/css/commons.css';
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";

const Password = ({className = ""}) => {
    const [showPassword, setShowPassword] = useState(false);
    const [strength, setStrength] = useState('');

    // 비밀번호 가시성 토글 함수
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    // 비밀번호 강도 평가 함수
    const checkPasswordStrength = (password) => {
      if (password.length > 8 && /[A-Z]/.test(password) && /[0-9]/.test(password)) {
        setStrength('strong');
      } else if (password.length > 5) {
        setStrength('medium');
      } else {
        setStrength('weak');
      }
    };

    // 비밀번호 변경 처리 함수
    const handlePasswordChange = (e) => {
      const newPassword = e.target.value;
      checkPasswordStrength(newPassword);
    };

    return (
      <div style={{ position: 'relative', width: '100%' }}>
        <input
          type={showPassword ? 'text' : 'password'}
          onChange={handlePasswordChange}
          className={`password-field ${className}`}
          placeholder="비밀번호를 입력하세요"
        />
        <button type="button" onClick={togglePasswordVisibility} className="toggle-button">
          {showPassword ? <AiFillEyeInvisible/> : <AiFillEye/>}
        </button>
        <div className="strength-meter">
          <div className={`strength-bar ${strength}`}></div>
        </div>
      </div>
    );
}

export default Password;
