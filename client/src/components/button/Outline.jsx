import React from 'react';
import '../../assets/css/commons.css';

const Outline = ({id= "", className = "", text = ""}) => {
    return (
        <button 
            id={id} 
            className={`outline-button ${className}`}>
            {text}
        </button>
    )
}

export default Outline;