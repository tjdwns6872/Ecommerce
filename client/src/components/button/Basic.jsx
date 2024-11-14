import React from 'react';
import '../../assets/css/commons.css';

const Basic = ({id= "", onClick="", className = "", text = ""}) => {
    return (
        <button 
            id={id} 
            className={`basic-button ${className}`}
            onClick={onClick}>
            {text}
        </button>
    )
}

export default Basic;