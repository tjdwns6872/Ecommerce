import React from 'react';
import '../../assets/css/commons.css';

const Error = ({placeholder = "", value = "", className = ""}) => {
    return (
        <div className="input-container">
            <input 
                type="text"
                className={`input-field error ${className}`}
                placeholder={placeholder}/>
        </div>
    )
}

export default Error;