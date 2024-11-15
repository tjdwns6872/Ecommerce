import Basic from "./Basic";
import Emphasis from "./Emphasis";
import Gradient from "./Gradient";
import IconButton from "./IconButton";
import Outline from "./Outline";
import Round from "./Round";

const ButtonFactory = {
    basic: (id, text, onClick, className) => {
        return <Basic id={id} text={text} onClick={onClick} className={className}></Basic>;
    },
    emphasis: (id, text, onClick, className) =>{
        return <Emphasis id={id} text={text} className={className}></Emphasis>
    },
    gradient: (id, text, onClick, className) =>{
        return <Gradient id={id} text={text} className={className}></Gradient>
    },
    outline: (id, text, onClick, className) =>{
        return <Outline id={id} text={text} className={className}></Outline>
    },
    round: (id, text, onClick, className) =>{
        return <Round id={id} text={text} className={className}></Round>
    },
    icon: (id, icon, onClick, className) =>{
        return <IconButton id={id} icon={icon} onClick={onClick} className={className}></IconButton>
    }
}

export default ButtonFactory;