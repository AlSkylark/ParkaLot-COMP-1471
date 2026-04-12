import { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { getGarage, reserve } from "../services/garageService";
import { Spinner, Card, Row, Col } from "react-bootstrap";
import EnumDropdown from "../components/EnumDropdown";
import { getProfile } from "../services/customerService";
import { useAuth } from "../context/AuthContext";

function Reserve() {
    const { isLoggedIn } = useAuth();
    const { id } = useParams();
    const navigate = useNavigate();
    const location = useLocation();

    const [submitting, setSubmitting] = useState(false);
    const [loading, setLoading] = useState(true);
    const [garage, setGarage] = useState(location.state ?? null);
    const [user, setUser] = useState(null);
    const [form, setForm] = useState({
        fullName: "",
        email: "",
        address: "",
        spaceTypeId: "",
        carPlate: "",
        selectedCarId: "",
        startDate: "",
        endDate: "",
        startTime: "",
        endTime: "",
    });

    // garage name
    useEffect(() => {
        if (location.state) {
            setLoading(false);
            return;
        }

        async function fetchGarage() {
            const garage = await getGarage(id);
            if (garage) {
                setGarage(garage);
                setLoading(false);
            } else {
                navigate("/404");
            }
        }

        fetchGarage();
    }, [id]);

    // logged user
    useEffect(() => {
        if (!isLoggedIn) return;

        async function fetchProfile() {
            const user = await getProfile();
            if (user) {
                setUser(user);
                setForm((prev) => ({
                    ...prev,
                    fullName: `${user.name} ${user.surname}` ?? "",
                    email: user.email ?? "",
                    address: user.address ?? "",
                }));
            }
        }

        fetchProfile();
    }, [isLoggedIn]);

    const isMultiDay =
        form.startDate && form.endDate && form.startDate !== form.endDate;

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSubmitting(true);
        try {
            const res = await reserve(id, form);
            alert(JSON.stringify(res));
        } finally {
            setSubmitting(false);
        }
    };

    const sectionLabel = "text-uppercase text-muted fw-semibold mb-2";
    const sectionLabelStyle = { fontSize: "0.7rem", letterSpacing: "0.08em" };

    return (
        <div className="min-vh-100 bg-light d-flex align-items-start justify-content-center py-5 px-3">
            {loading ? (
                <div className="min-vh-100 d-flex align-items-center justify-content-center">
                    <Spinner animation="border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </Spinner>
                </div>
            ) : (
                <Card className="shadow-sm w-100" style={{ maxWidth: "720px" }}>
                    <Card.Header className="bg-white border-bottom pt-4 pb-3 px-4">
                        <p className={sectionLabel} style={sectionLabelStyle}>
                            Make a Reservation
                        </p>
                        <h1 className="h3 fw-bold text-dark mb-1">
                            {garage.name}
                        </h1>
                        <p
                            className="text-muted mb-0"
                            style={{ fontSize: "0.9rem" }}
                        >
                            📍 {garage.address}
                        </p>
                    </Card.Header>

                    <Card.Body className="px-4 py-4">
                        <form onSubmit={handleSubmit}>
                            {/* Personal details */}
                            <div className="bg-light rounded-3 p-3 mb-4">
                                <p
                                    className={sectionLabel}
                                    style={sectionLabelStyle}
                                >
                                    Your Details
                                </p>
                                {!user && (
                                    <p
                                        className="text-muted mb-3"
                                        style={{ fontSize: "0.85rem" }}
                                    >
                                        Browsing as guest — fill in your details
                                        below.
                                    </p>
                                )}
                                <Row className="g-3">
                                    <Col md={12}>
                                        <label className="form-label fw-semibold">
                                            Full Name
                                        </label>
                                        <input
                                            name="fullName"
                                            className="form-control"
                                            value={form.fullName}
                                            onChange={handleChange}
                                            readOnly={!!user}
                                            required
                                        />
                                    </Col>
                                    <Col md={6}>
                                        <label className="form-label fw-semibold">
                                            Email
                                        </label>
                                        <input
                                            name="email"
                                            type="email"
                                            className="form-control"
                                            value={form.email}
                                            onChange={handleChange}
                                            readOnly={!!user}
                                            required
                                        />
                                    </Col>
                                    <Col md={6}>
                                        <label className="form-label fw-semibold">
                                            Address
                                        </label>
                                        <input
                                            name="address"
                                            className="form-control"
                                            value={form.address}
                                            onChange={handleChange}
                                            readOnly={!!user}
                                            required
                                        />
                                    </Col>
                                </Row>
                            </div>

                            {/* Parking details */}
                            <div className="bg-light rounded-3 p-3 mb-4">
                                <p
                                    className={sectionLabel}
                                    style={sectionLabelStyle}
                                >
                                    Parking Details
                                </p>
                                <Row className="g-3">
                                    <Col md={12}>
                                        <label className="form-label fw-semibold">
                                            Space Type
                                        </label>
                                        <EnumDropdown
                                            name="space-types"
                                            value={form.spaceTypeId}
                                            onChange={(e) =>
                                                setForm({
                                                    ...form,
                                                    spaceTypeId: e.target.value,
                                                })
                                            }
                                        />
                                    </Col>

                                    {user?.cars?.length > 0 && (
                                        <Col md={6}>
                                            <label className="form-label fw-semibold">
                                                Saved Car
                                            </label>
                                            <select
                                                name="selectedCarId"
                                                className="form-select"
                                                value={form.selectedCarId}
                                                onChange={handleChange}
                                            >
                                                <option value="">
                                                    Select a saved car...
                                                </option>
                                                {user.cars.map((car) => (
                                                    <option
                                                        key={car.id}
                                                        value={car.id}
                                                    >
                                                        {car.plate}
                                                    </option>
                                                ))}
                                            </select>
                                        </Col>
                                    )}

                                    <Col md={user?.cars?.length > 0 ? 6 : 12}>
                                        <label className="form-label fw-semibold">
                                            {user?.cars?.length > 0
                                                ? "Or enter a plate"
                                                : "Car Plate Number"}
                                        </label>
                                        <input
                                            name="carPlate"
                                            className="form-control"
                                            value={form.carPlate}
                                            onChange={handleChange}
                                        />
                                        {user?.cars?.length > 0 && (
                                            <div className="form-text">
                                                For a guest car or one-off
                                                booking.
                                            </div>
                                        )}
                                    </Col>
                                </Row>
                            </div>

                            {/* Date & time */}
                            <div className="bg-light rounded-3 p-3 mb-4">
                                <p
                                    className={sectionLabel}
                                    style={sectionLabelStyle}
                                >
                                    Arrival & Departure
                                </p>
                                <Row className="g-3">
                                    <Col md={6}>
                                        <label className="form-label fw-semibold">
                                            Start Date
                                        </label>
                                        <input
                                            name="startDate"
                                            type="date"
                                            className="form-control"
                                            value={form.startDate}
                                            onChange={handleChange}
                                            required
                                        />
                                    </Col>
                                    <Col md={6}>
                                        <label className="form-label fw-semibold">
                                            End Date
                                        </label>
                                        <input
                                            name="endDate"
                                            type="date"
                                            className="form-control"
                                            value={form.endDate}
                                            min={form.startDate}
                                            onChange={handleChange}
                                            required
                                        />
                                    </Col>

                                    {form.startDate &&
                                        form.endDate &&
                                        (isMultiDay ? (
                                            <Col md={12}>
                                                <div
                                                    className="d-flex align-items-center gap-2 text-muted"
                                                    style={{
                                                        fontSize: "0.875rem",
                                                    }}
                                                >
                                                    <span>📅</span>
                                                    <span>
                                                        Multi-day booking — full
                                                        days will be reserved.
                                                    </span>
                                                </div>
                                            </Col>
                                        ) : (
                                            <>
                                                <Col md={6}>
                                                    <label className="form-label fw-semibold">
                                                        Start Time
                                                    </label>
                                                    <input
                                                        name="startTime"
                                                        type="time"
                                                        className="form-control"
                                                        value={form.startTime}
                                                        onChange={handleChange}
                                                        required
                                                    />
                                                </Col>
                                                <Col md={6}>
                                                    <label className="form-label fw-semibold">
                                                        End Time
                                                    </label>
                                                    <input
                                                        name="endTime"
                                                        type="time"
                                                        className="form-control"
                                                        value={form.endTime}
                                                        min={form.startTime}
                                                        onChange={handleChange}
                                                        required
                                                    />
                                                </Col>
                                            </>
                                        ))}
                                </Row>
                            </div>

                            <div className="d-flex justify-content-end gap-2">
                                <button
                                    type="button"
                                    className="btn btn-outline-secondary px-4 py-2 fw-semibold"
                                    onClick={() => navigate(`/garages/${id}`)}
                                >
                                    Go Back
                                </button>
                                <button
                                    type="submit"
                                    className="btn btn-primary px-4 py-2 fw-semibold"
                                    disabled={submitting}
                                >
                                    {submitting ? (
                                        <>
                                            <Spinner
                                                animation="border"
                                                size="sm"
                                                className="me-2"
                                            />
                                            Getting your spot...
                                        </>
                                    ) : (
                                        "Continue"
                                    )}
                                </button>
                            </div>
                        </form>
                    </Card.Body>
                </Card>
            )}
        </div>
    );
}

export default Reserve;
