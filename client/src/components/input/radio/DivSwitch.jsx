import React from 'react';
import '../../../assets/css/commons.css';
const DivSwitch = ({selected=true}) => {

    return (
        <div className="custom-radio-box">
            <div className="radio-buttons">
                <button
                className={`radio-button ${selected === "button1" ? "active" : ""}`}
                onClick={()=>({})}
                >
                버튼1
                </button>
                <button
                className={`radio-button ${selected === "button2" ? "active" : ""}`}
                onClick={()=>({})}
                >
                버튼2
                </button>
            </div>
            <div className="content">
                {selected === "button1" ? (
                <p>버튼1 선택 내용</p>
                ) : (
                <p>버튼2 선택 내용</p>
                )}
            </div>
        </div>
    );
};

export default DivSwitch;