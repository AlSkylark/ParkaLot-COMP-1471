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
import Success from "./pages/success";
import QuotePage from "./pages/QuotePage";
import About from "./pages/about";
import Services from "./pages/services";

function App() {
    const { isLoggedIn } = useAuth();

    function withLayout(children) {
        return <MainLayout>{children}</MainLayout>;
    }

    return (
        <HashRouter>
            <Routes>
                <Route path="/" element={withLayout(<Home />)} />
                <Route path="/garages" element={<Navigate to="/" />} />

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

                <Route path="/success" element={withLayout(<Success />)} />

                <Route
                    path="/reservation/:id"
                    element={withLayout(<Reserve />)}
                />

                <Route path="/quote" element={withLayout(<QuotePage />)} />

                <Route path="/about" element={withLayout(<About />)} />

                <Route path="/services" element={withLayout(<Services />)} />
            </Routes>
        </HashRouter>
    );
}

export default App;
