import React from 'react';
import '../../assets/css/commons.css';

/**
 * 
 * @param {문구} placeholder 
 * @param {값} value
 * @param {클래스명} className
 * @returns 
 */
const Basic = ({id = "", placeholder = "", value = "", className = "", disabled=false}) => {
    return (
        <div className="input-container">
            <input 
                id={id}
                type="text"
                disabled={disabled}
                className={`input-field ${className}`}
                placeholder={placeholder}/>
        </div>
    );
  };
export default Basic;
  