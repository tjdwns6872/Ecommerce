import React from 'react';
import '../../assets/css/commons.css';

function Error(){
    return (
        <div>
            <input type="text" class="input-field error" placeholder=""/>
            <input type="text" class="input-field" placeholder="" disabled/>
        </div>
    )
}

export default Error;