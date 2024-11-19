import React from 'react';
import '../../../assets/css/commons.css';

const Round = ({id = "", placeholder="", value = "", className = ""}) => {
    return (
        <label class="checkbox-wrapper checkbox-round">{placeholder}
            <input id={id} type="checkbox" className={className}/>
            <span class="checkmark"></span>
        </label>
    );
  };
export default Round;
  