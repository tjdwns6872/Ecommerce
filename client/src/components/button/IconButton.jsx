import React from 'react';
import '../../assets/css/commons.css';

const IconButton = ({ id = "", icon: Icon, onClick, className = ""}) => {
  return (
    <button 
        id={id}
        className={`icon-button ${className}`}
        onClick={onClick}>
      <Icon className="icon" />
    </button>
  );
};

export default IconButton;
