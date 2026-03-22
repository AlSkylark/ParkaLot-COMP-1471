import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { signin } from "../services/customerService";
import { useAuth } from "../context/AuthContext";

function Login() {
  const [email, setEmail] = useState("");
  const [validated, setValidated] = useState(false);
  const [loginError, setLoginError] = useState(false);

  const { login } = useAuth();
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    const form = e.currentTarget;

    if (!form.checkValidity()) {
      setValidated(true);
      return;
    }

    const success = await signin(email);
    if (success) {
      login(); //context update
      navigate("/");
    } else {
      setLoginError(true);
    }
    setValidated(true);
  }

  function getEmailClass() {
    if (!validated) return "form-control";
    if (loginError || !email) return "form-control is-invalid";
    return "form-control is-valid";
  }

  return (
    <div className="container d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow" style={{ width: "350px" }}>
        <form onSubmit={handleSubmit} noValidate>
          <h3 className="text-center mb-3">Login</h3>
          <div className="mb-3">
            <input
              type="text"
              className={getEmailClass()}
              placeholder="Email"
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
                setLoginError(false);
                setValidated(false);
              }}
              required
            />
            <div className="invalid-feedback">
              {loginError
                ? "Invalid email or password."
                : "Please enter an email."}
            </div>
          </div>
          <div className="mb-3">
            <input
              type="password"
              className="form-control"
              placeholder="Password"
            />
          </div>
          <button className="btn btn-outline-success w-100" type="submit">
            Login
          </button>
        </form>
        <hr />
        <button
          className="btn btn-outline-success w-100"
          onClick={() => navigate("/create-account")}
        >
          Create Account
        </button>
      </div>
    </div>
  );
}

export default Login;
