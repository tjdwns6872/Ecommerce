import React from 'react';
import { BiSolidCategoryAlt, BiSolidHome, BiSolidHeart, BiSolidUser, BiLogoSnapchat } from "react-icons/bi";
import '../assets/css/commons.css';

function Header(){
    return (
    <>
    <header class="top-header">
        <div class="top-header-container">
            <div class="logo">
                <h1>Novoa</h1>
            </div>
            <div class="header-actions">
                <input type="text" class="search-input" placeholder="검색" />
                <button class="cart-button">장바구니</button>
                <button class="notification-button">알림</button>
            </div>
        </div>
    </header>
    <div class="bottom-header">
        <nav class="nav">
            <ul>
                <li><a href="/#"><BiSolidCategoryAlt/><br/>카테고리</a></li>
                <li><a href="/#"><BiLogoSnapchat/><br/>스냅</a></li>
                <li><a href="/"><BiSolidHome/><br/>홈</a></li>
                <li><a href="/#"><BiSolidHeart/><br/>좋아요</a></li>
                <li><a href="/#"><BiSolidUser/><br/>마이</a></li>
            </ul>
        </nav>
    </div>
    </>
    )
}

export default Header;