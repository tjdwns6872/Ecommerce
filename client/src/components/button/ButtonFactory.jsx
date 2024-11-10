import Basic from "./Basic";
import Emphasis from "./Emphasis";
import Gradient from "./Gradient";
import Outline from "./Outline";
import Round from "./Round";

const ButtonFactory = {
    basic: (id, className, text) => {
        return <Basic id={id} className={className} text={text}></Basic>;
    },
    emphasis: (id, className, text) =>{
        return <Emphasis id={id} className={className} text={text}></Emphasis>
    },
    gradient: (id, className, text) =>{
        return <Gradient id={id} className={className} text={text}></Gradient>
    },
    outline: (id, className, text) =>{
        return <Outline id={id} className={className} text={text}></Outline>
    },
    round: (id, className, text) =>{
        return <Round id={id} className={className} text={text}></Round>
    },
}

export default ButtonFactory;