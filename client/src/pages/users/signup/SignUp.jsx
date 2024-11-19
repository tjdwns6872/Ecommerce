import React, { useState, useEffect } from 'react';
import SignUpData from './SignUpData';
import SignUpCode from './SignUpCode';
import SignUpAgreement from './SignUpAgreement';
import { Link } from 'react-router-dom'; // 로그인 링크를 위한 react-router-dom 사용

function SignUp() {
  const [currentStep, setCurrentStep] = useState(1);

  useEffect(() => {
    const urlParams = new URL(window.location.href).searchParams;
    const step = parseInt(urlParams.get('step'), 10) || 1;
    setCurrentStep(step);
  }, []);

  return (
    <div className="signup-container">
      <div className="signup-header">
        <h2>회원가입</h2>
        <p>Novoa에서 새로운 계정을 만들어보세요.</p>
      </div>
      {currentStep === 1 && <SignUpAgreement />}
      {currentStep === 2 && <SignUpCode />}
      {currentStep === 3 && <SignUpData />}
      <div className="signup-footer">
        <p>이미 계정이 있으신가요? <Link to="/login">로그인</Link></p>
      </div>
    </div>
  );
}

export default SignUp;
