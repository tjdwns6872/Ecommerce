import React, { useState, useEffect } from 'react';
import '../../assets/css/commons.css';
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";

const Password = ({id = "", placeholder = "", initialValue = "", className = "", disabled=false, onValueChange}) => {
    const [showPassword, setShowPassword] = useState(false);
    const [strength, setStrength] = useState('');

    // 비밀번호 가시성 토글 함수
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const [value, setValue] = useState(initialValue);

    useEffect(() => {
        setValue(initialValue);
    }, [initialValue]);

    const handleChange = (e) => {
        const newValue = e.target.value;
        setValue(newValue);
        if (onValueChange) {
            onValueChange(id, newValue);
        }
    };

    return (
      <div className='input-container' style={{ position: 'relative' }}>
        <input
          id={id}
          type={showPassword ? 'text' : 'password'}
          disabled={disabled}
          className={`password-field ${className}`}
          placeholder={placeholder}
          value={value}
          onChange={handleChange}
        />
        <button type="button" onClick={togglePasswordVisibility} className="toggle-button">
          {showPassword ? <AiFillEyeInvisible/> : <AiFillEye/>}
        </button>
        {/* <div className="strength-meter">
          <div className={`strength-bar ${strength}`}></div>
        </div> */}
      </div>
    );
}

export default Password;
