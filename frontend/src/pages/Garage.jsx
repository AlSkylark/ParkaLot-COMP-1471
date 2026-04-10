import { useEffect, useState } from "react";
import { Badge, Card, Col, Row, Spinner } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import { getGarage } from "../services/garageService";

const availabilityConfig = {
    HIGH_AVAILABILITY: { label: "High Availability", variant: "success" },
    MEDIUM_AVAILABILITY: { label: "Medium Availability", variant: "warning" },
    LOW_AVAILABILITY: { label: "Low Availability", variant: "danger" },
    FULL: { label: "Full", variant: "secondary" },
};

function Garage() {
    const { id } = useParams();
    const [garage, setGarage] = useState();
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const avail =
        availabilityConfig[garage?.availability] ?? availabilityConfig.FULL;

    useEffect(() => {
        async function fetchGarage(id) {
            const garage = await getGarage(id);
            if (garage) {
                setGarage(garage);
                setLoading(false);
            } else {
                navigate("/404");
            }
        }
        fetchGarage(id);
    }, []);

    return (
        <div className="min-vh-100 bg-light d-flex align-items-start justify-content-center py-5 px-3">
            {loading ? (
                <div className="min-vh-100 d-flex align-items-center justify-content-center">
                    <Spinner animation="border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </Spinner>
                </div>
            ) : (
                <Card className="shadow-sm w-100" style={{ maxWidth: "860px" }}>
                    {/* Header */}
                    <Card.Header className="bg-white border-bottom pt-4 pb-3 px-4">
                        <div className="d-flex justify-content-between align-items-start flex-wrap gap-3">
                            <div>
                                <p
                                    className="text-uppercase text-muted fw-semibold mb-1"
                                    style={{
                                        fontSize: "0.7rem",
                                        letterSpacing: "0.08em",
                                    }}
                                >
                                    Garage Details
                                </p>
                                <h1 className="h3 fw-bold text-dark mb-2">
                                    {garage.name}
                                </h1>
                                <p className="text-muted mb-0">
                                    📍 {garage.address}
                                </p>
                            </div>
                            <Badge
                                bg={avail.variant}
                                text={
                                    avail.variant == "warning"
                                        ? "black"
                                        : "white"
                                }
                                className="fs-6 px-3 py-2 rounded-pill fw-semibold"
                            >
                                {avail.label}
                            </Badge>
                        </div>
                    </Card.Header>

                    <Card.Body className="px-4 py-4">
                        {/* Info row */}
                        <Row className="g-3 mb-4">
                            <Col md={6}>
                                <div className="bg-light rounded-3 p-3 h-100">
                                    <p
                                        className="text-uppercase text-muted fw-semibold mb-2"
                                        style={{
                                            fontSize: "0.7rem",
                                            letterSpacing: "0.08em",
                                        }}
                                    >
                                        Description
                                    </p>
                                    <p
                                        className="text-secondary mb-0"
                                        style={{
                                            fontSize: "0.9rem",
                                            lineHeight: "1.6",
                                        }}
                                    >
                                        A secure, covered multi-storey facility
                                        conveniently located in the city centre.
                                        CCTV monitored 24/7 with disabled access
                                        on all levels.
                                    </p>
                                </div>
                            </Col>
                            <Col md={6}>
                                <div className="bg-light rounded-3 p-3 h-100">
                                    <p
                                        className="text-uppercase text-muted fw-semibold mb-2"
                                        style={{
                                            fontSize: "0.7rem",
                                            letterSpacing: "0.08em",
                                        }}
                                    >
                                        Contact
                                    </p>
                                    <div className="d-flex flex-column gap-2">
                                        <a
                                            href="tel:+441234567890"
                                            className="text-decoration-none text-secondary"
                                            style={{ fontSize: "0.9rem" }}
                                        >
                                            📞 +44 1234 567 890
                                        </a>
                                        <a
                                            href="tel:+441234567891"
                                            className="text-decoration-none text-secondary"
                                            style={{ fontSize: "0.9rem" }}
                                        >
                                            📞 +44 1234 567 891{" "}
                                            <span
                                                className="text-muted"
                                                style={{ fontSize: "0.8rem" }}
                                            >
                                                (emergencies)
                                            </span>
                                        </a>
                                    </div>
                                </div>
                            </Col>
                        </Row>

                        {/* Map */}
                        <div className="rounded-3 overflow-hidden mb-4">
                            <iframe
                                title="Garage location map"
                                width="100%"
                                height="420"
                                frameBorder="0"
                                style={{ border: 0, display: "block" }}
                                src={
                                    "https://www.google.com/maps/embed/v1/place?key=AIzaSyB2NIWI3Tv9iDPrlnowr_0ZqZWoAQydKJU&q=" +
                                    encodeURIComponent(garage.address) +
                                    "&maptype=roadmap"
                                }
                                allowFullScreen
                            />
                        </div>

                        {/* CTA */}
                        <div className="d-flex justify-content-end">
                            <button
                                className={`btn ${garage.availability === "FULL" ? "btn-secondary" : "btn-primary"} px-4 py-2 fw-semibold`}
                                disabled={garage.availability === "FULL"}
                            >
                                {garage.availability === "FULL"
                                    ? "Currently Full"
                                    : "Make a Reservation"}
                            </button>
                        </div>
                    </Card.Body>
                </Card>
            )}
        </div>
    );
}

export default Garage;
