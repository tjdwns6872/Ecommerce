import Basic from "./Basic";
import Error from "./Error";
import Password from "./Password";

const InputFactory = {
    basic: (id = "", placeholder = "", value = "", className = "") => {
        return <Basic id={id} type="text" placeholder={placeholder} value={value} className={className} />;
    },
    error: (id = "", placeholder = "", value = "", className = "") => {
        return <Error type="text" placeholder={placeholder} value={value} className={className} />;
    },
    password: (id = "", className = "") => {
        return <Password id={id} className={className} />;
    }
}

export default InputFactory;