import React from 'react';
import '../../../assets/css/commons.css';

const Switch = ({id = "", placeholder="", value = "", className = ""}) => {
    return (
        <label class="checkbox-switch">
            <input id={id} type="checkbox" className={className}/>
            <span class="slider"></span>
        </label>
    );
  };
export default Switch;
  