import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';
import signUpEvent from '../../../assets/js/signUpEvent';
import pageMove from '../../../assets/js/pageMove';
import React, { useState, useEffect } from 'react';

function SignUpCode(status={}) {

  const [changeBtn, setChangeBtn] = useState(2);

  const [formData, setFormData] = useState({
    userPhone: '',
    userCode: '',
  });

  const handleValueChange = (id, value) => {
    setFormData(prevData => ({
      ...prevData,
      [id]: value
    }));
  };

  const buttonChange = async (e) => {
    var data;
    if(e === 0){
      data = await signUpEvent.authCodeSend();
      if(data.status === 200){
        setChangeBtn(e+1);
      }
    }else if(e === 1){
      data = await signUpEvent.codeCheck();
      if(data.status === 200){
        setChangeBtn(e+1);
      }
    }else{
      pageMove.paramsUrl('http://localhost:3000/signup?', 'step=3');
    }
  }

  return (
    <div className='signup-div signup-code'>
    <div className="form-group">
    {InputFactory.basic('userPhone', '휴대폰 번호', formData.userPhone, 'input-field', false, handleValueChange)}
    </div>
    <div className="form-group">
      {InputFactory.basic('userCode', '인증번호를 입력하세요', formData.userCode, 'input-field', false, handleValueChange)}
      {changeBtn === 0 && ButtonFactory.basic('code-btn','인증번호 전송', () => buttonChange(changeBtn), 'w-100')}
      {changeBtn === 1 && ButtonFactory.basic('check-btn','인증번호 확인', () => buttonChange(changeBtn), 'w-100')}
      {changeBtn === 2 && ButtonFactory.basic('check-btn','다음단계', () => buttonChange(changeBtn), 'w-100')}
    </div>
    </div>
  )
}

export default SignUpCode;