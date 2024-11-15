import React, { useState } from 'react';
import InputFactory from '../../components/input/InputFactory';
import ButtonFactory from '../../components/button/ButtonFactory';
import { Link } from 'react-router-dom'; // 로그인 링크를 위한 react-router-dom 사용

function SignUp() {

  return (
    <div className="signup-container">
      <div className="signup-header">
        <h2>회원가입</h2>
        <p>Novoa에서 새로운 계정을 만들어보세요.</p>
      </div>
      <div>
        <div className="form-group">
          <label>
            <input 
              type="checkbox" 
              name="serviceTerms" 
            />
            서비스 이용 약관에 동의합니다.
          </label>
        </div>
        <div className="form-group">
          <label>
            <input 
              type="checkbox" 
              name="advertisingConsent" 
            />
            광고 수신에 동의합니다.
          </label>
        </div>
        <div className="form-group">
          {ButtonFactory.basic('signup-btn','회원가입', () => '', 'w-100')}
        </div>
      </div>
      <div>
        <div className="form-group">
          {InputFactory.basic('user-phone', '휴대폰 번호', "", 'input-field')}
        </div>
        <div className="form-group">
          {InputFactory.basic('user-code', '인증번호를 입력하세요', "", 'input-field')}
          {ButtonFactory.basic('code-btn','인증번호 전송', () => '', '')}
        </div>
      </div>
      <div className="signup-form">
        <form>
          <div className="form-group">
            {InputFactory.basic('user-email', '이메일을 입력하세요', "", 'input-field')}
          </div>

          <div className="form-group">
            {InputFactory.password('user-pw', '비밀번호를 입력하세요', "", 'input-field')}
          </div>

          <div className="form-group">
            {InputFactory.password('user-pw-check', '비밀번호 확인', "", 'input-field')}
          </div>

          <div className="form-group">
            {InputFactory.basic('user-name', '이름을 입력하세요', "", 'input-field')}
          </div>

          <div className="form-group">
            {InputFactory.basic('user-birth-date', '생년월일 입력하세요', "", 'input-field')}
          </div>

          <div className="form-group">
            {InputFactory.basic('user-referral-code', '추천인 코드를 입력하세요', "", 'input-field')}
          </div>

          <div className="form-group">
            {ButtonFactory.basic('signup-btn','회원가입', () => '', 'w-100')}
          </div>
        </form>
      </div>
      <div className="signup-footer">
        <p>이미 계정이 있으신가요? <Link to="/login">로그인</Link></p>
      </div>
    </div>
  );
}

export default SignUp;
