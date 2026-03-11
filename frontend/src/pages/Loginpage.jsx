import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  return (
    <div className="container d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow" style={{ width: "350px" }}>
        <h3 className="text-center mb-3">Login</h3>

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
