import React, {
  createContext,
  useContext,
  useState,
  useMemo,
} from 'react';
import PropType from 'prop-types';
import { useApi } from './ApiProvider';

const AuthenticationContext = createContext();

export default function AuthenticationProvider({ children }) {
  const api = useApi();
  const [user, setUser] = useState(null);
  const [authenticationError, setAuthenticationError] = useState();

  const login = async (username, password) => {
    setAuthenticationError(undefined);
    try {
      const token = await api.login(username, password);
      console.log(token);
      setUser({ username: token.username, userType: token.userType });
    } catch (e) {
      setAuthenticationError('Invalid credentials');
    }
  };

  const value = useMemo(() => ({
    user, login, authenticationError,
  }), [user, login, authenticationError]); // Add dependencies here

  return (
    <AuthenticationContext.Provider value={value}>
      {children}
    </AuthenticationContext.Provider>
  );
}

AuthenticationProvider.propTypes = {
  children: PropType.node.isRequired,
};

export function useAuthentication() {
  return useContext(AuthenticationContext);
}
