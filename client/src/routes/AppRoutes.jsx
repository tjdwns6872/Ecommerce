import React from "react";
import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import Login from '../pages/users/Login';
import Find from "../pages/users/find/Find";
import SignUp from "../pages/users/signup/SignUp";
import MyPage from '../pages/users/myPage/MyPage';
import NotPage from "../pages/commons/NotPage";

function AppRoutes() {
    var token = localStorage.getItem('accessToken');
    return (
        <>
            <Routes>
                <Route path="/home" element={<Home/>}></Route>
            </Routes>
            {token !== null ?
                <Routes>
                    <Route path="/*" element={<NotPage/>}></Route>
                    <Route path="/mypage" element={<MyPage/>}></Route>
                </Routes>
                :
                <Routes>
                    <Route path="*" element={<Login/>}></Route>
                    <Route path="/login" element={<Login/>}></Route>
                    <Route path="/find" element={<Find/>}></Route>
                    <Route path="/signup" element={<SignUp/>}></Route>
                </Routes>
            }
        </>
    )
}

export default AppRoutes;