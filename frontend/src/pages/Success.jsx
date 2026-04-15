import { Navigate, useLocation } from "react-router-dom";
import QRCode from "react-qr-code";
import { useState } from "react";
import { Card, Row, Col, Badge } from "react-bootstrap";

const spaceTypeConfig = {
    0: { label: "Normal", icon: "🅿️" },
    1: { label: "Electric", icon: "⚡" },
};

export default function Success() {
    const { state } = useLocation();
    const passedContract = state?.contract;
    const [contract] = useState(passedContract);

    if (!contract) return <Navigate to="/" />;

    const { quote, invoiceDate } = contract;
    const isMultiDay = quote.dateFrom !== quote.dateTo;
    const spaceType = spaceTypeConfig[quote.spaceType];

    const sectionLabel = "text-uppercase text-muted fw-semibold mb-2";
    const sectionLabelStyle = { fontSize: "0.7rem", letterSpacing: "0.08em" };

    return (
        <div className="min-vh-100 bg-light d-flex align-items-start justify-content-center py-5 px-3">
            <div className="w-100" style={{ maxWidth: "720px" }}>
                {/* Success banner */}
                <div className="text-center mb-4">
                    <div
                        className="d-inline-flex align-items-center justify-content-center rounded-circle bg-success mb-3"
                        style={{ width: "56px", height: "56px" }}
                    >
                        <span style={{ fontSize: "1.5rem" }}>✓</span>
                    </div>
                    <h1 className="h3 fw-bold text-dark mb-1">
                        You're all set!
                    </h1>
                    <p className="text-muted">
                        Hi {quote.name}, your space has been reserved. See you
                        there!
                    </p>
                </div>

                <Card className="shadow-sm border-0 overflow-hidden">
                    <Card.Body className="p-0">
                        {/* QR + reference */}
                        <div
                            className="d-flex flex-column flex-sm-row align-items-center gap-4 p-4"
                            style={{
                                background: "#f0f7ff",
                                borderBottom: "1px solid #cce0ff",
                            }}
                        >
                            <div
                                className="p-3 bg-white rounded-3 shadow-sm"
                                style={{ flexShrink: 0 }}
                            >
                                <QRCode value={quote.quoteNumber} size={120} />
                            </div>
                            <div>
                                <p
                                    className={sectionLabel}
                                    style={sectionLabelStyle}
                                >
                                    Booking Reference
                                </p>
                                <p
                                    className="fw-bold text-primary mb-1"
                                    style={{
                                        fontSize: "2rem",
                                        letterSpacing: "0.1em",
                                    }}
                                >
                                    {quote.quoteNumber}
                                </p>
                                <p
                                    className="text-muted mb-0"
                                    style={{ fontSize: "0.85rem" }}
                                >
                                    Show this QR code or reference number on
                                    arrival.
                                </p>
                            </div>
                        </div>

                        <div className="p-4">
                            {/* Space details */}
                            <div className="bg-light rounded-3 p-3 mb-4">
                                <p
                                    className={sectionLabel}
                                    style={sectionLabelStyle}
                                >
                                    Your Space
                                </p>
                                <Row className="g-3">
                                    <Col xs={4}>
                                        <p
                                            className="text-muted mb-1"
                                            style={{ fontSize: "0.8rem" }}
                                        >
                                            Space
                                        </p>
                                        <p className="fw-bold text-dark mb-0">
                                            {quote.spaceCode}
                                        </p>
                                    </Col>
                                    <Col xs={4}>
                                        <p
                                            className="text-muted mb-1"
                                            style={{ fontSize: "0.8rem" }}
                                        >
                                            Floor
                                        </p>
                                        <p className="fw-bold text-dark mb-0">
                                            {quote.spaceFloor}
                                        </p>
                                    </Col>
                                    <Col xs={4}>
                                        <p
                                            className="text-muted mb-1"
                                            style={{ fontSize: "0.8rem" }}
                                        >
                                            Type
                                        </p>
                                        <p className="fw-bold text-dark mb-0">
                                            {spaceType?.icon}{" "}
                                            {spaceType?.label ?? "Unknown"}
                                        </p>
                                    </Col>
                                </Row>
                            </div>

                            {/* Dates */}
                            <div className="bg-light rounded-3 p-3 mb-4">
                                <p
                                    className={sectionLabel}
                                    style={sectionLabelStyle}
                                >
                                    Arrival & Departure
                                </p>
                                <Row className="g-3">
                                    <Col xs={6}>
                                        <p
                                            className="text-muted mb-1"
                                            style={{ fontSize: "0.8rem" }}
                                        >
                                            From
                                        </p>
                                        <p className="fw-bold text-dark mb-0">
                                            {quote.dateFrom}
                                        </p>
                                        {!isMultiDay && quote.timeFrom && (
                                            <p
                                                className="text-muted mb-0"
                                                style={{ fontSize: "0.85rem" }}
                                            >
                                                {quote.timeFrom}
                                            </p>
                                        )}
                                    </Col>
                                    <Col xs={6}>
                                        <p
                                            className="text-muted mb-1"
                                            style={{ fontSize: "0.8rem" }}
                                        >
                                            To
                                        </p>
                                        <p className="fw-bold text-dark mb-0">
                                            {quote.dateTo}
                                        </p>
                                        {!isMultiDay && quote.timeTo && (
                                            <p
                                                className="text-muted mb-0"
                                                style={{ fontSize: "0.85rem" }}
                                            >
                                                {quote.timeTo}
                                            </p>
                                        )}
                                    </Col>
                                    {isMultiDay && (
                                        <Col xs={12}>
                                            <div
                                                className="d-flex align-items-center gap-2 text-muted"
                                                style={{ fontSize: "0.875rem" }}
                                            >
                                                <span>📅</span>
                                                <span>
                                                    Multi-day booking — full
                                                    days reserved.
                                                </span>
                                            </div>
                                        </Col>
                                    )}
                                </Row>
                            </div>

                            {/* Price & invoice */}
                            <div
                                className="rounded-3 p-3 mb-4 d-flex justify-content-between align-items-center"
                                style={{
                                    background: "#f0f7ff",
                                    border: "1px solid #cce0ff",
                                }}
                            >
                                <div>
                                    <p
                                        className={sectionLabel}
                                        style={sectionLabelStyle}
                                    >
                                        Total Due
                                    </p>
                                    <p
                                        className="text-muted mb-0"
                                        style={{ fontSize: "0.8rem" }}
                                    >
                                        Payment due by {invoiceDate}
                                    </p>
                                </div>
                                <p
                                    className="fw-bold text-primary mb-0"
                                    style={{ fontSize: "2rem" }}
                                >
                                    £{Number(quote.price).toFixed(2)}
                                </p>
                            </div>

                            {/* Actions */}
                            <div className="d-flex justify-content-end gap-2">
                                <button
                                    className="btn btn-outline-secondary px-4 py-2 fw-semibold"
                                    onClick={() => window.print()}
                                >
                                    Print / Save
                                </button>
                                <a
                                    href="/"
                                    className="btn btn-primary px-4 py-2 fw-semibold"
                                >
                                    Back to Home
                                </a>
                            </div>
                        </div>
                    </Card.Body>
                </Card>
            </div>
        </div>
    );
}
