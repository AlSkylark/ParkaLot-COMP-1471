import { useNavigate } from "react-router-dom";
import { Card, Badge } from "react-bootstrap";

const availabilityConfig = {
    HIGH_AVAILABILITY: { label: "High Availability", variant: "success" },
    MEDIUM_AVAILABILITY: { label: "Medium Availability", variant: "warning" },
    LOW_AVAILABILITY: { label: "Low Availability", variant: "danger" },
    FULL: { label: "Full", variant: "secondary" },
};

function GarageCard({ id, image, name, address, availability }) {
    const navigate = useNavigate();
    const avail = availabilityConfig[availability] ?? availabilityConfig.FULL;

    return (
        <Card
            className="h-100 shadow-sm border-0 overflow-hidden"
            style={{ cursor: "pointer" }}
            onClick={() => navigate(`/garages/${id}`)}
        >
            <div style={{ height: "180px", overflow: "hidden" }}>
                <img
                    src={image}
                    alt={name}
                    className="w-100 h-100"
                    style={{ objectFit: "cover" }}
                />
            </div>
            <Card.Body className="d-flex flex-column p-3">
                <div className="d-flex justify-content-between align-items-start mb-2">
                    <h5 className="fw-bold text-dark mb-0">{name}</h5>
                    <Badge
                        bg={avail.variant}
                        className="ms-2 rounded-pill"
                        style={{ fontSize: "0.7rem", whiteSpace: "nowrap" }}
                    >
                        {avail.label}
                    </Badge>
                </div>
                <p className="text-muted mb-3" style={{ fontSize: "0.875rem" }}>
                    📍 {address}
                </p>
                <button
                    className={`btn mt-auto fw-semibold ${
                        availability === "FULL"
                            ? "btn-outline-secondary disabled"
                            : "btn-primary"
                    }`}
                    onClick={(e) => {
                        e.stopPropagation();
                        navigate(`/garages/${id}`);
                    }}
                    disabled={availability === "FULL"}
                >
                    {availability === "FULL" ? "Currently Full" : "View & Book"}
                </button>
            </Card.Body>
        </Card>
    );
}

export default GarageCard;
