import React from 'react';
import Basic from "./Basic";
import Error from "./Error";
import Password from "./Password";
import CheckBasic from "./checkBox/Basic";
import CheckRound from "./checkBox/Round";
import CheckSwitch from "./checkBox/Switch";

const InputFactory = {
    basic: (id = "", placeholder = "", initialValue = "", className = "", disabled=false, onValueChange) => {
        return <Basic id={id} type="text" placeholder={placeholder} initialValue={initialValue} className={className} disabled={disabled} onValueChange={onValueChange}/>;
    },
    error: (id = "", placeholder = "", initialValue = "", className = "", disabled=false, onValueChange) => {
        return <Error id={id} type="text" placeholder={placeholder} initialValue={initialValue} className={className} disabled={disabled} onValueChange={onValueChange}/>;
    },
    password: (id = "", placeholder = "", initialValue = "", className = "", disabled=false, onValueChange) => {
        return <Password id={id} placeholder={placeholder} initialValue={initialValue} className={className} disabled={disabled} onValueChange={onValueChange}/>;
    },
    CheckBasic: (id = "", placeholder="", value = "", className = "", disabled=false, onChange) => {
        return <CheckBasic id={id} placeholder={placeholder} value={value} className={className} disabled={disabled} onChange={onChange}/>;
    },
    CheckRound: (id = "", placeholder="", value = "", className = "", disabled=false, onChange) => {
        return <CheckRound id={id} placeholder={placeholder} value={value} className={className} disabled={disabled} onChange={onChange}/>;
    },
    CheckSwitch: (id = "", placeholder="", value = "", className = "", disabled=false, onChange) => {
        return <CheckSwitch id={id} placeholder={placeholder} value={value} className={className} disabled={disabled} onChange={onChange}/>;
    }
}

export default InputFactory;