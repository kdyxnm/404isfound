import React from 'react';
import {
  BrowserRouter, Route, Routes, Navigate,
} from 'react-router-dom';
import ApiProvider from './contexts/ApiProvider';
import AuthenticationProvider, { useAuthentication } from './contexts/AuthenticationProvider';
import Login from './pages/loginPage';
import AdminDashboard from './pages/adminDashboard';
import CustomerDashboard from './pages/customerDashBoard';
import CompanyUserDashboard from './pages/companyUserDashboard';

const withRoleCheck = (Component, expectedRole) => function (props) {
  const { user } = useAuthentication();

  if (!user || user.userType !== expectedRole) {
    return <Navigate to="/login" />;
  }

  // eslint-disable-next-line react/jsx-props-no-spreading
  return <Component {...props} />;
};

const AdminDashboardWithCheck = withRoleCheck(AdminDashboard, 'admin');
const CustomerDashboardWithCheck = withRoleCheck(CustomerDashboard, 'customer');
const CompanyUserDashboardWithCheck = withRoleCheck(CompanyUserDashboard, 'companyUser');

function App() {
  return (
    <ApiProvider>
      <AuthenticationProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/admin-dashboard" element={<AdminDashboardWithCheck />} />
            <Route path="/customer-dashboard" element={<CustomerDashboardWithCheck />} />
            <Route path="/companyuser-dashboard" element={<CompanyUserDashboardWithCheck />} />
            <Route path="*" element={<Navigate to="/login" replace />} />
          </Routes>
        </BrowserRouter>
      </AuthenticationProvider>
    </ApiProvider>
  );
}

export default App;
