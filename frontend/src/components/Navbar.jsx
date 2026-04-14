import { useNavigate } from "react-router-dom";
import { signout } from "../services/customerService";
import { Dropdown, Button, ButtonGroup } from "react-bootstrap";
import { useAuth } from "../context/AuthContext";

function Navbar() {
    const { isLoggedIn, logout } = useAuth();
    const navigate = useNavigate();

    function handleSignout() {
        signout();
        logout();
        navigate("/");
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-success px-4 shadow-sm">
            {/* LOGO */}
            <span
                className="navbar-brand fw-bold"
                style={{ cursor: "pointer" }}
                onClick={() => navigate("/")}
            >
                ParkaLot
            </span>

            {/* MENU */}
            <div className="ms-auto d-flex align-items-center gap-3">
                <button
                    className="btn btn-link text-white"
                    onClick={() => navigate("/")}
                >
                    Home
                </button>

                <button
                    className="btn btn-link text-white"
                    onClick={() => navigate("/about")}
                >
                    About
                </button>

                <button
                    className="btn btn-link text-white"
                    onClick={() => navigate("/services")}
                >
                    Services
                </button>

                {isLoggedIn ? (
                    <Dropdown as={ButtonGroup}>
                        <Button
                            variant="primary"
                            onClick={() => navigate("/profile")}
                        >
                            My Profile
                        </Button>

                        <Dropdown.Toggle split variant="primary" />

                        <Dropdown.Menu>
                            <Dropdown.Item onClick={handleSignout}>
                                Sign out
                            </Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                ) : (
                    <button
                        className="btn btn-primary"
                        onClick={() => navigate("/login")}
                    >
                        Login
                    </button>
                )}
            </div>
        </nav>
    );
}

export default Navbar;
