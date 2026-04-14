import { useLocation, useNavigate } from "react-router-dom";

function Success() {
    const location = useLocation();
    const navigate = useNavigate();

    const { message, garage } = location.state || {};

    return (
        <div className="d-flex align-items-center justify-content-center min-vh-100 bg-light">
            <div className="card shadow p-5 text-center" style={{ maxWidth: "500px" }}>

                <h2 className="text-success mb-3">✅ Success!</h2>

                <p className="mb-2 fw-semibold">{message}</p>
                <p className="text-muted mb-4">
                    Your reservation at <strong>{garage}</strong> is confirmed.
                </p>

                <button
                    className="btn btn-primary"
                    onClick={() => navigate("/")}
                >
                    Go Home
                </button>
            </div>
        </div>
    );
}

export default Success;