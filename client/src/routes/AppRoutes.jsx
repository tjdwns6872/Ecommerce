import React from "react";
import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import Login from '../pages/users/Login';

function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/login" element={<Login/>}></Route>
        </Routes>
    )
}

export default AppRoutes;