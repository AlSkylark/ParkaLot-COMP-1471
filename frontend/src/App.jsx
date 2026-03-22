import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import MainLayout from "./Layouts/Mainlayout";

import Home from "./pages/Homepage";
import Booking from "./pages/Bookingpage";
import Login from "./pages/Loginpage";
import CreateAccount from "./pages/CreateAccount";
import { checkedSignedIn } from "./services/customerService";
import Profile from "./pages/Profile";
import { useAuth } from "./context/AuthContext";

function App() {
  const { isLoggedIn } = useAuth();

  function withLayout(children) {
    return <MainLayout>{children}</MainLayout>;
  }

  return (
    <BrowserRouter basename={import.meta.env.VITE_BASE_URL}>
      <Routes>
        <Route path="/" element={withLayout(<Home />)} />
        <Route path="/booking" element={withLayout(<Booking />)} />
        <Route path="/create-account" element={withLayout(<CreateAccount />)} />

        <Route
          path="/login"
          element={withLayout(
            isLoggedIn ? <Navigate to="/" replace /> : <Login />,
          )}
        />
        <Route
          path="/profile"
          element={withLayout(
            isLoggedIn ? <Profile /> : <Navigate to="/login" replace />,
          )}
        ></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
