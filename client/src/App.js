import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import AppRoutes from './routes/AppRoutes';
import Header from './components/Header';
import Footer from './components/Footer';

function App() {
  return (
    <Router>
      <Header /> {/* 상단 헤더 */}
      <main className="main-content">
        <AppRoutes /> {/* 메인 화면 라우팅 */}
      </main>
      <Footer/>
    </Router>
  );
}

export default App;
