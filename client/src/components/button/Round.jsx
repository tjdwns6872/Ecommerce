import React from 'react';
import '../../assets/css/commons.css';

const Round = ({id= "", className = "", text = ""}) => {
    return (
        <button 
            id={id} 
            className={`round-button ${className}`}>
            {text}
        </button>
    )
}

export default Round;