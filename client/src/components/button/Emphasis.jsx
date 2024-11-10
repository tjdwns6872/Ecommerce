import React from 'react';
import '../../assets/css/commons.css';

const Emphasis = ({id= "", className = "", text = ""}) => {
    return (
        <button 
            id={id} 
            className={`emphasis-button ${className}`}>
            {text}
        </button>
    )
}

export default Emphasis;