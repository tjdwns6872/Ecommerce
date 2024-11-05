import React from 'react';
import api from '../../services/api';

function Login(){
    return (
        <>
            <div>Login</div>
            {api.test()}
        </>
    )
}

export default Login;