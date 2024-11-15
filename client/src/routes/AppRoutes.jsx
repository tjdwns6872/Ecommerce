import React from "react";
import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import Login from '../pages/users/Login';
import Find from "../pages/users/Find";
import SignUp from "../pages/users/SignUp";

function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/login" element={<Login/>}></Route>
            <Route path="/find" element={<Find/>}></Route>
            <Route path="/signup" element={<SignUp/>}></Route>
        </Routes>
    )
}

export default AppRoutes;