import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { reserve } from "../services/garageService";
import { Spinner } from "react-bootstrap";

function QuotePage() {
    const { state } = useLocation();
    const navigate = useNavigate();

    const [accepted, setAccepted] = useState(false);
    const [loading, setLoading] = useState(false);

    if (!state) return <p className="text-center mt-5">No quote available</p>;

    const { form, garage } = state;

    // 💡 SIMPLE PRICE CALCULATION
    const pricePerHour = 5;

    let total = 0;

    if (form.startDate === form.endDate) {
        const start = new Date(`1970-01-01T${form.startTime}`);
        const end = new Date(`1970-01-01T${form.endTime}`);
        const hours = (end - start) / (1000 * 60 * 60);
        total = hours * pricePerHour;
    } else {
        const days =
            (new Date(form.endDate) - new Date(form.startDate)) /
            (1000 * 60 * 60 * 24);
        total = days * 24 * pricePerHour;
    }

    const handleConfirm = async () => {
        setLoading(true);
        try {
            await reserve(garage.id, form);

            navigate("/success", {
                state: {
                    message: "Reservation confirmed!",
                    garage: garage.name,
                },
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
            <div className="card shadow p-4 w-100" style={{ maxWidth: "520px" }}>

                <h3 className="fw-bold mb-3 text-center">Reservation Summary</h3>

                <p className="text-muted text-center mb-3">
                    {garage.name} — {garage.address}
                </p>

                <hr />

                <p><strong>Date:</strong> {form.startDate} → {form.endDate}</p>
                <p><strong>Time:</strong> {form.startTime} → {form.endTime}</p>

                <h4 className="text-primary mt-3 text-center">
                    Total: £{total.toFixed(2)}
                </h4>

                {/* TERMS */}
                <div className="form-check mt-4">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="terms"
                        onChange={(e) => setAccepted(e.target.checked)}
                    />
                    <label className="form-check-label" htmlFor="terms">
                        I agree to the terms & conditions
                    </label>
                </div>

                {/* BUTTONS */}
                <div className="d-flex justify-content-between mt-4">
                    <button
                        className="btn btn-outline-secondary"
                        onClick={() => navigate(-1)}
                    >
                        Back
                    </button>

                    <button
                        className="btn btn-success"
                        disabled={!accepted || loading}
                        onClick={handleConfirm}
                    >
                        {loading ? (
                            <>
                                <Spinner size="sm" className="me-2" />
                                Processing...
                            </>
                        ) : (
                            "Confirm Reservation"
                        )}
                    </button>
                </div>

            </div>
        </div>
    );
}

export default QuotePage;