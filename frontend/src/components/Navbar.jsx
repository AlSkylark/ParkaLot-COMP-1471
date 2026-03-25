import { useNavigate } from "react-router-dom";
import { signout } from "../services/customerService";
import { Dropdown, Button, ButtonGroup } from "react-bootstrap";
import { useAuth } from "../context/AuthContext";

function Navbar() {
  const { isLoggedIn, logout } = useAuth();
  const navigate = useNavigate();

  async function handleSignout() {
    signout();
    logout(); //context update
    navigate("/");
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4">
      <span
        className="navbar-brand fw-bold"
        style={{ cursor: "pointer" }}
        onClick={() => navigate("/")}
      >
        ParkaLot
      </span>

      <div className="ms-auto">
        {isLoggedIn ? (
          <Dropdown as={ButtonGroup}>
            <Button variant="primary" onClick={() => navigate("/profile")}>
              My profile
            </Button>
            <Dropdown.Toggle split variant="primary"></Dropdown.Toggle>
            <Dropdown.Menu>
              <Dropdown.Item as="button" onClick={() => handleSignout()}>
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
