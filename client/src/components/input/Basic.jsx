import React, { useState, useEffect } from 'react';
import '../../assets/css/commons.css';

/**
 * 
 * @param id 태그 id 
 * @param placeholder 태그 placeholder 
 * @param initialValue 태그 value
 * @param className 태그 class
 * @param onValueChange 태그 value change function
 * @returns 
 */
const Basic = ({id = "", placeholder = "", initialValue = "", className = "", disabled=false, onValueChange}) => {
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
                className={`input-field ${className}`}
                placeholder={placeholder}
                value={value}
                onChange={handleChange}/>
        </div>
    );
};

export default Basic;