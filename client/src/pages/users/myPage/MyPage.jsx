import React, { useEffect, useState } from 'react';
import myPageEvent from '../../../assets/js/user/myPageEvent';
import '../../../assets/css/commons.css';

const MyPage = () => {

    const [userDat, setUsetData] = useState();

    useEffect(() => {
        // console.log(localStorage.getItem('accessToken'));
        // setUsetData(myPageEvent.userData());
    }, []);

    return (
        <div className="mypage-container">
            {/* 상단 프로필 영역 */}
            <div className="profile-section">
                <div className="profile-image">
                    <img
                    src="https://via.placeholder.com/100"
                    alt="프로필"
                    className="profile-pic"
                    />
            </div>
                <div className="profile-info">
                    <h2 className="username">홍길동</h2>
                    <p className="user-email">example@example.com</p>
                    <button className="edit-profile-btn">프로필 수정</button>
                </div>
            </div>
            {/* 메뉴 영역 */}
            <div className="menu-section">
                <ul className="menu-list">
                    <li className="menu-item">주문 내역</li>
                    <li className="menu-item">찜한 상품</li>
                    <li className="menu-item">알림 설정</li>
                    <li className="menu-item">로그아웃</li>
                </ul>
            </div>
        </div>
    );
};

export default MyPage;

