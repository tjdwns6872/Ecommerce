import React, { useState } from 'react';
import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';
import signUpEvent from '../../../assets/js/signUpEvent';
import pageMove from '../../../assets/js/pageMove';

function SignUpData() {
  var json = pageMove.paramsToJson(window.location.href);
  const [formData, setFormData] = useState({
    ecUsersEmail: json['ecUsersEmail'],
    ecUsersPassword: '',
    ecUsersPasswordCheck: '',
    ecUsersName: '',
    ecUsersPhone: json['ecUsersPhone'],
    ecUsersBirthDate: '',
    ecUsersReferralCode: ''
  });

  const handleValueChange = (id, value) => {
    setFormData(prevData => ({
      ...prevData,
      [id]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    var data = signUpEvent.createUser(formData);
    if(data.status === 200){
      window.location.href="/login";
    }
  };

  return (
    <div className='signup-div signup-data'>
      <div className="signup-form">
        <form id="signup-form" onSubmit={handleSubmit}>
          <div className="form-group">
            {InputFactory.basic('ecUsersEmail', '이메일을 입력하세요', formData.ecUsersEmail, 'input-field', false, handleValueChange)}
          </div>
          <div className="form-group">
            {InputFactory.password('ecUsersPassword', '비밀번호를 입력하세요', formData.ecUsersPassword, 'input-field', handleValueChange)}
          </div>
          <div className="form-group">
            {InputFactory.password('ecUsersPasswordCheck', '비밀번호 확인', formData.ecUsersPasswordCheck, 'input-field', handleValueChange)}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersName', '이름을 입력하세요', formData.ecUsersName, 'input-field', false, handleValueChange)}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersPhone', '전화번호를 입력하세요', formData.ecUsersPhone, 'input-field', true, handleValueChange)}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersBirthDate', '생년월일 입력하세요', formData.ecUsersBirthDate, 'input-field', false, handleValueChange)}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersReferralCode', '추천인 코드를 입력하세요', formData.ecUsersReferralCode, 'input-field', false, handleValueChange)}
          </div>
        </form>
        <div className="form-group">
          {ButtonFactory.basic('signup-btn','회원가입', handleSubmit, 'w-100')}
        </div>
      </div>
    </div>
  )
}
export default SignUpData;