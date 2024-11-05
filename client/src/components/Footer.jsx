import React from 'react';
import '../assets/css/commons.css';

function Footer(){
    return (
        <footer className="footer">
            <p>© 2024 Novoa. 모든 권리 보유.</p>
            <nav className="footer-nav">
                <ul>
                    <li><a href="/#">개인정보 처리방침</a></li>
                    <li><a href="/#">이용 약관</a></li>
                    <li><a href="/#">고객 지원</a></li>
                </ul>
            </nav>
        </footer>
    )
}

export default Footer;