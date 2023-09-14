import React, { useState } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuthentication } from '../contexts/AuthenticationProvider';
// import { useApi } from '../contexts/ApiProvider';
const loginPage = () => {
  const { user, login, authenticationError } = useAuthentication();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  // const api = useApi();
  const handleLogin = async () => {
    await login(username, password);
  };

  if (user) {
    console.log(user.userType);
    switch (user.userType) {
      case 'admin':
        return <Navigate to="/admin-dashboard" />;
      case 'customer':
        return <Navigate to="/customer-dashboard" />;
      case 'companyUser':
        return <Navigate to="/companyuser-dashboard" />;
      default:
        return <Navigate to="/login" />;
    }
  }

  return (
    <div>
      <h1>Share Trading System</h1>
      <input type="text" placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
      <button type="button" onClick={handleLogin}>Login</button>
      {authenticationError && <div>{authenticationError}</div>}
    </div>
  );
};

export default loginPage;
