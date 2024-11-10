import React from 'react';
import '../../assets/css/commons.css';

const Gradient = ({id= "", className = "", text = ""}) => {
    return (
        <button 
            id={id} 
            className={`gradient-button ${className}`}>
            {text}
        </button>
    )
}

export default Gradient;