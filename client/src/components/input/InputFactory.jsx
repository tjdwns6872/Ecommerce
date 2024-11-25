import Basic from "./Basic";
import Error from "./Error";
import Password from "./Password";
import CheckBasic from "./checkBox/Basic";
import CheckRound from "./checkBox/Round";
import CheckSwitch from "./checkBox/Switch";

const InputFactory = {
    basic: (id = "", placeholder = "", value = "", className = "", disabled=false) => {
        return <Basic id={id} type="text" placeholder={placeholder} value={value} className={className} disabled={disabled}/>;
    },
    error: (id = "", placeholder = "", value = "", className = "") => {
        return <Error type="text" placeholder={placeholder} value={value} className={className} />;
    },
    password: (id = "", className = "") => {
        return <Password id={id} className={className} />;
    },
    CheckBasic: (id = "", placeholder="", value = "", className = "") => {
        return <CheckBasic id={id} placeholder={placeholder} value={value} className={className} />;
    },
    CheckRound: (id = "", placeholder="", value = "", className = "") => {
        return <CheckRound id={id} placeholder={placeholder} value={value} className={className} />;
    },
    CheckSwitch: (id = "", placeholder="", value = "", className = "") => {
        return <CheckSwitch id={id} placeholder={placeholder} value={value} className={className} />;
    }
}

export default InputFactory;