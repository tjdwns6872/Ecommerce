import InputFactory from '../../../components/input/InputFactory';
import React, { useState } from 'react';

function FindId(){

    const [formData, setFormData] = useState({
        userPhone: '',
        userCode: '',
        userName: '',
    });

    const handleValueChange = (id, value) => {
        setFormData(prevData => ({
        ...prevData,
        [id]: value
        }));
    };

    return (
        <div className='signup-div signup-code'>
        <div className="form-group">
            {InputFactory.basic('userName', '이름', formData.userName, 'input-field', false, handleValueChange)}
            {InputFactory.basic('userPhone', '휴대폰 번호', formData.userPhone, 'input-field', false, handleValueChange)}
        </div>
            
        </div>
    )
}
export default FindId;