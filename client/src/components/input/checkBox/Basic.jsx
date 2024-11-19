import React from 'react';
import '../../../assets/css/commons.css';

const Basic = ({id = "", placeholder="", value = "", className = ""}) => {
    return (
      <label class="checkbox-wrapper">{placeholder}
        <input id={id} type="checkbox" className={className}/>
        <span class="checkmark"></span>
      </label>
    );
  };
export default Basic;
  