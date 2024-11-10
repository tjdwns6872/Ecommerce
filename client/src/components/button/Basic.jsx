import React from 'react';
import '../../assets/css/commons.css';

const Basic = ({id= "", className = "", text = ""}) => {
    return (
        <button 
            id={id} 
            className={`basic-button ${className}`}>
            {text}
        </button>
    )
}

export default Basic;