import React, { useState, useEffect } from "react";
import FindId from "./FindId";
import FindPw from "./FindPw";
import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';
import findEvent from '../../../assets/js/user/FindEvent';
import { Link } from 'react-router-dom'; // 로그인 링크를 위한 react-router-dom 사용

function Find(){

    const [changeBtn, setChangeBtn] = useState(0);
    const buttonChange = async (e) => {
        var type = formData.changeType?"emailFind":"passwordFind";
        var data;
        if(e === 0){
            data = await findEvent.findCodeSend(type);
            if(data.status === 200){
                handleValueChange('certId', data.data)
                setChangeBtn(e+1);
            }
        }else if(e === 1){
            data = await findEvent.codeCheck(formData.certId);
            if(data.status === 200){
                setChangeBtn(e+1);
            }
        }else if(e === 2){
            data = await findEvent.findUserData(type);
            if(data.status === 200){
                handleValueChange('result', data);
                handleValueChange('resultPage', true);
            }
        }
    }

    const [formData, setFormData] = useState({
        userCode: '',
        changeType: true,
        certId: '',
        result: '',
        resultPage: false,
    });
    const handleValueChange = (id, value) => {
        setFormData(prevData => ({
        ...prevData,
        [id]: value
        }));
    };

    return (
        <div className="signup-container">
            <div className="signup-header">
                <h2>아이디/비밀번호 찾기</h2>
                <div className="custom-radio-box">
                    <div className="radio-buttons">
                        <button
                        className={`radio-button ${formData.changeType === true ? "active" : ""}`}
                        onClick={() => handleValueChange('changeType', true)}
                        data-check="true"
                        >
                        아이디 찾기
                        </button>
                        <button
                        className={`radio-button ${formData.changeType === false ? "active" : ""}`}
                        onClick={() => handleValueChange('changeType', false)}
                        data-check="false"
                        >
                        비밀번호 찾기
                        </button>
                    </div>
                    <div className="content">
                        {formData.resultPage === false ? (
                            <div>
                                {formData.changeType === true ? (
                                    <FindId/>
                                    ) : (
                                    <FindPw/>
                                )}
                                <div className="form-group">
                                    {InputFactory.basic('userCode', '인증번호를 입력하세요', formData.userCode, 'input-field', false, handleValueChange)}
                                    {changeBtn === 0 && ButtonFactory.basic('code-btn','인증번호 전송', () => buttonChange(changeBtn), 'w-100')}
                                    {changeBtn === 1 && ButtonFactory.basic('check-btn','인증번호 확인', () => buttonChange(changeBtn), 'w-100')}
                                    {changeBtn === 2 && ButtonFactory.basic('check-btn','확인', () => buttonChange(changeBtn), 'w-100')}
                                </div>
                            </div>
                        ):(
                            <div>
                                <div className="form-group">
                                    <h2>{formData.result.data}</h2>
                                </div>
                            </div>
                        )}
                        
                    </div>
                </div>
            </div>
            <div className="signup-footer">
                <p>이미 계정이 있으신가요? <Link to="/login">로그인</Link></p>
            </div>
        </div>
    )
}

export default Find;