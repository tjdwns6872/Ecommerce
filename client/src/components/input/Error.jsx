import React, { useState, useEffect } from 'react';
import '../../assets/css/commons.css';

const Error = ({id = "", placeholder = "", initialValue = "", className = "", disabled=false, onValueChange}) => {
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
        <div className="input-container">
            <input 
                id={id}
                type="text"
                disabled={disabled}
                className={`input-field error ${className}`}
                placeholder={placeholder}
                value={value}
                onChange={handleChange}/>
        </div>
    )
}

export default Error;