import { Routes, Route, Navigate, HashRouter } from "react-router-dom";
import { useAuth } from "./context/AuthContext";

import MainLayout from "./Layouts/Mainlayout";
import Home from "./pages/Homepage";
import Login from "./pages/Loginpage";
import CreateAccount from "./pages/CreateAccount";
import Profile from "./pages/Profile";
import Garage from "./pages/Garage";
import NotFound from "./pages/404";
import Reserve from "./pages/Reserve";


function App() {
    const { isLoggedIn } = useAuth();

    function withLayout(children) {
        return <MainLayout>{children}</MainLayout>;
    }

    return (
        <HashRouter>
            <Routes>
                <Route path="/" element={withLayout(<Home />)} />
                <Route
                    path="/create-account"
                    element={withLayout(<CreateAccount />)}
                />

                <Route
                    path="/login"
                    element={withLayout(
                        isLoggedIn ? <Navigate to="/" replace /> : <Login />,
                    )}
                />
                <Route
                    path="/profile"
                    element={withLayout(
                        isLoggedIn ? (
                            <Profile />
                        ) : (
                            <Navigate to="/login" replace />
                        ),
                    )}
                ></Route>

                <Route
                    path="/garages/:id"
                    element={withLayout(<Garage />)}
                ></Route>

                <Route
                    path="/garages/:id/reserve"
                    element={withLayout(<Reserve />)}
                />

                <Route path="/404" element={withLayout(<NotFound />)} />
            </Routes>
        </HashRouter>
    );
}

export default App;
