// context/AuthContext.jsx
import { createContext, useContext, useState } from "react";
import { checkedSignedIn } from "../services/customerService";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [isLoggedIn, setIsLoggedIn] = useState(checkedSignedIn());

  function login() {
    setIsLoggedIn(true);
  }

  function logout() {
    setIsLoggedIn(false);
  }

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

// custom hook for easy consumption
export function useAuth() {
  return useContext(AuthContext);
}
