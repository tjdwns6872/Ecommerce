import React from 'react';

function FindResult({ data }) {
    return (
        <div className="find-result-container">
            <div className="find-result-message">
                <span>{data}</span>
            </div>
            <div className="find-result-actions">
                <button onClick={() => window.location.href = '/login'}>
                    로그인 화면으로
                </button>
                <button onClick={() => window.location.href = '/signup'}>
                    회원가입
                </button>
            </div>
        </div>
    );
}

export default FindResult;
