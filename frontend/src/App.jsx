import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainLayout from "./Layouts/Mainlayout";

import Home from "./pages/Homepage";
import Booking from "./pages/Bookingpage";
import Login from "./pages/Loginpage";
import CreateAccount from "./pages/CreateAccount";

function App() {
  return (
    <BrowserRouter basename={import.meta.env.VITE_BASE_URL}>
      <Routes>
        <Route
          path="/"
          element={
            <MainLayout>
              <Home />
            </MainLayout>
          }
        />

        <Route
          path="/booking"
          element={
            <MainLayout>
              <Booking />
            </MainLayout>
          }
        />

        <Route
          path="/login"
          element={
            <MainLayout>
              <Login />
            </MainLayout>
          }
        />

        <Route
          path="/create-account"
          element={
            <MainLayout>
              <CreateAccount />
            </MainLayout>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
