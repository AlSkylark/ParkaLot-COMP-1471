import { useNavigate, useLocation } from "react-router-dom";
import { signout } from "../services/customerService";
import { Dropdown, ButtonGroup } from "react-bootstrap";
import { useAuth } from "../context/AuthContext";

function Navbar() {
    const { isLoggedIn, logout } = useAuth();
    const navigate = useNavigate();
    const location = useLocation();

    function handleSignout() {
        signout();
        logout();
        navigate("/");
    }

    const navLink = (path, label) => (
        <button
            className="btn btn-link text-decoration-none fw-semibold px-2"
            style={{
                color:
                    location.pathname === path
                        ? "#fff"
                        : "rgba(255,255,255,0.65)",
                borderBottom:
                    location.pathname === path
                        ? "2px solid #fff"
                        : "2px solid transparent",
                borderRadius: 0,
            }}
            onClick={() => navigate(path)}
        >
            {label}
        </button>
    );

    return (
        <nav
            className="navbar navbar-expand-lg px-4 shadow-sm"
            style={{ backgroundColor: "#0f2d1f", minHeight: "64px" }}
        >
            {/* Logo */}
            <span
                className="navbar-brand fw-bold fs-4 me-4"
                style={{
                    cursor: "pointer",
                    color: "#fff",
                    letterSpacing: "-0.02em",
                }}
                onClick={() => navigate("/")}
            >
                Parka<span style={{ color: "#22c55e" }}>Lot</span>
            </span>

            {/* Nav links */}
            <div className="d-flex align-items-center gap-1">
                {navLink("/", "Home")}
                {navLink("/about", "About")}
                {navLink("/services", "Services")}
            </div>

            {/* Right side */}
            <div className="ms-auto d-flex align-items-center gap-3">
                {isLoggedIn ? (
                    <Dropdown as={ButtonGroup}>
                        <button
                            className="btn btn-primary fw-semibold px-3"
                            onClick={() => navigate("/profile")}
                            style={{ borderRadius: "8px 0 0 8px" }}
                        >
                            My Profile
                        </button>
                        <Dropdown.Toggle
                            split
                            variant="primary"
                            style={{ borderRadius: "0 8px 8px 0" }}
                        />
                        <Dropdown.Menu align="end">
                            <Dropdown.Item onClick={() => navigate("/profile")}>
                                👤 Profile
                            </Dropdown.Item>
                            <Dropdown.Divider />
                            <Dropdown.Item
                                onClick={handleSignout}
                                className="text-danger"
                            >
                                Sign out
                            </Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                ) : (
                    <button
                        className="btn btn-primary fw-semibold px-4"
                        style={{ borderRadius: "8px" }}
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
