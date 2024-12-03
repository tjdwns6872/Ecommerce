import React, { useState, useEffect } from "react";
import FindId from "./FindId";
import FindPw from "./FindPw"
import { Link } from 'react-router-dom'; // 로그인 링크를 위한 react-router-dom 사용

function Find(){

    const [tabChange, setTabChange] = useState(true);

    const changeTab = (id) => {
        if(tabChange.toString() !== id.target.dataset.check){
            setTabChange(!tabChange)
        }
    }

    return (
        <div className="signup-container">
            <div className="signup-header">
                <h2>아이디/비밀번호 찾기</h2>
                <div className="custom-radio-box">
                    <div className="radio-buttons">
                        <button
                        className={`radio-button ${tabChange === true ? "active" : ""}`}
                        onClick={changeTab}
                        data-check="true"
                        >
                        아이디 찾기
                        </button>
                        <button
                        className={`radio-button ${tabChange === false ? "active" : ""}`}
                        onClick={changeTab}
                        data-check="false"
                        >
                        비밀번호 찾기
                        </button>
                    </div>
                    <div className="content">
                        {tabChange === true ? (
                            <FindId/>
                            ) : (
                            <FindPw/>
                            )}
                    </div>
                </div>
            </div>
            <div className="signup-footer">
                <p>이미 계정이 있으신가요? <Link to="/login">로그인</Link></p>
            </div>
        </div>
    )
}

export default Find;