import React from 'react';
import '../../assets/css/commons.css';

/**
 * 
 * @param {문구} placeholder 
 * @param {값} value
 * @param {클래스명} className
 * @returns 
 */
const Basic = ({id = "", placeholder = "", value = "", className = ""}) => {
    return (
        <div className="input-container">
            <input 
                id={id}
                type="text"
                className={`input-field ${className}`}
                placeholder={placeholder}/>
        </div>
    );
  };
export default Basic;
  