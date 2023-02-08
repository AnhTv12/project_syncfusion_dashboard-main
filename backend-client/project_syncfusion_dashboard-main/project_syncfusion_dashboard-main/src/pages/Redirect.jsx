import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { Navigate, useSearchParams } from "react-router-dom";
import { authenticate, updateLogin } from '../app/user/userSlice'
import { useNavigate } from 'react-router-dom';
const Redirect = () => {
  const navigate = useNavigate()
  const [searchParams] = useSearchParams();
  const dispatch = useDispatch();
  useEffect(() => {
    if (searchParams?.get('code')) {
      if (searchParams?.get('code')) {
        const code = searchParams?.get('code');
        const headers = new Headers();
        headers.append('Content-type', 'application/x-www-form-urlencoded');
        headers.append('Authorization', 'Basic bWVzc2FnZXMtY2xpZW50OnNlY3JldA==');
        const initialUrl = 'http://localhost:8081/oauth2/token?client_id=client&redirect_uri=http://127.0.0.1:3000/authorized&grant_type=authorization_code';
        const url = `${initialUrl}&code=${code}`;
        console.log(code)
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            headers
        }).then(async (response) => {
            const token = await response.json();
            sessionStorage.setItem('id_token', token.id_token);
            dispatch(authenticate());
            dispatch(updateLogin());
            navigate("/ecommerce" )
        }).catch((err) => {
            console.log(err);
        })
      
      }
    }
    else if (!searchParams?.get('code')) {

      window.location.href = 'http://localhost:8081/oauth2/authorize?response_type=code&client_id=messages-client&scope=openid&state=4HifQnO1Urd5oQmcYY_Yb0GMJeewPzEgczEL0ac8X7c%3D&redirect_uri=http://127.0.0.1:3000/authorized&nonce=aJM72tyagPaO_7zxlqKWx-9NaJE7w7vfmnDmh83sstk'
    }
  }, [])
  return (
    <>
      <div>redirecting...</div>

    </>
  )
}

export default Redirect;