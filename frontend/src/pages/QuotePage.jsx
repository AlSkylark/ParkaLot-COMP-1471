import { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import {
    acceptQuote,
    cancelQuote,
    getQuote,
} from "../services/contractService";
import { Badge, Card, Col, Row, Spinner } from "react-bootstrap";

/* QuoteDto
  String name,
  String quoteNumber,
  String spaceCode,
  String spaceFloor,
  int spaceType,
  BigDecimal price,
  LocalDate dateFrom,
  LocalDate dateTo,
  LocalTime timeFrom,
  LocalTime timeTo
*/
export default function QuotePage() {
    const { state } = useLocation();
    const navigate = useNavigate();

    const { quoteNo } = useParams();
    const passedQuote = state?.quote ?? null;

    const [quote, setQuote] = useState(passedQuote);
    const [loading, setLoading] = useState(!passedQuote);
    const [submitting, setSubmitting] = useState(false);
    const [discount, setDiscount] = useState("");
    const [discountApplied, setDiscountApplied] = useState(false);
    const [notFound, setNotFound] = useState(false);

    if (!quoteNo) return <p className="text-center mt-5">No quote available</p>;

    //if there's no quote param we'll try to fetch the quoteNo from url
    useEffect(() => {
        setLoading(true);
        try {
            if (quote) return;

            async function fetchQuote() {
                const data = await getQuote(quoteNo);
                setQuote(data);
            }

            fetchQuote();
        } catch {
            setNotFound(true);
        } finally {
            setLoading(false);
        }
    }, [quoteNo]);

    const handleApplyDiscount = async () => {
        // wire up discount logic here
        setDiscountApplied(true);
    };

    const handleConfirm = async () => {
        setSubmitting(true);
        try {
            const contract = await acceptQuote(quote.quoteNumber);
            navigate("/success", { state: { contract: contract } });
        } finally {
            setSubmitting(false);
        }
    };

    const handleCancel = async () => {
        setSubmitting(true);
        try {
            await cancelQuote(quote.quoteNumber);
            navigate("/");
        } finally {
            setSubmitting(false);
        }
    };

    const spaceTypeConfig = {
        0: { label: "Normal", icon: "🅿️" },
        1: { label: "Electric", icon: "⚡" },
    };

    const sectionLabel = "text-uppercase text-muted fw-semibold mb-2";
    const sectionLabelStyle = { fontSize: "0.7rem", letterSpacing: "0.08em" };

    if (loading) {
        return (
            <div className="min-vh-100 d-flex align-items-center justify-content-center bg-light">
                <Spinner animation="border" role="status">
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            </div>
        );
    }

    if (notFound || (!loading && !quote)) {
        return (
            <div className="min-vh-100 bg-light d-flex align-items-center justify-content-center">
                <div className="text-center">
                    <h2 className="h4 fw-bold text-dark mb-2">
                        No quote available
                    </h2>
                    <p className="text-muted mb-4">
                        This quote may have expired or doesn't exist.
                    </p>
                    <button
                        className="btn btn-primary px-4"
                        onClick={() => navigate("/")}
                    >
                        Back to Home
                    </button>
                </div>
            </div>
        );
    }

    const isMultiDay = quote.dateFrom !== quote.dateTo;

    return (
        <div className="min-vh-100 bg-light d-flex align-items-start justify-content-center py-5 px-3">
            <Card
                className="shadow-sm w-100 border-0"
                style={{ maxWidth: "720px" }}
            >
                {/* Header */}
                <Card.Header className="bg-white border-bottom pt-4 pb-3 px-4">
                    <p className={sectionLabel} style={sectionLabelStyle}>
                        Quote #{quote.quoteNumber}
                    </p>
                    <h1 className="h3 fw-bold text-dark mb-1">
                        Your Reservation Quote
                    </h1>
                    <p
                        className="text-muted mb-0"
                        style={{ fontSize: "0.9rem" }}
                    >
                        Hi {quote.name} — here are your details. Review and
                        confirm below.
                    </p>
                </Card.Header>

                <Card.Body className="px-4 py-4">
                    {/* Space details */}
                    <div className="bg-light rounded-3 p-3 mb-4">
                        <p className={sectionLabel} style={sectionLabelStyle}>
                            Your Space
                        </p>
                        <Row className="g-3">
                            <Col xs={6}>
                                <p
                                    className="text-muted mb-1"
                                    style={{ fontSize: "0.8rem" }}
                                >
                                    Space Code
                                </p>
                                <p className="fw-bold text-dark mb-0">
                                    {quote.spaceCode}
                                </p>
                            </Col>
                            <Col xs={6}>
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
                            <Col xs={12}>
                                <p
                                    className="text-muted mb-1"
                                    style={{ fontSize: "0.8rem" }}
                                >
                                    Space Type
                                </p>
                                <p className="fw-bold text-dark mb-0">
                                    {spaceTypeConfig[quote.spaceType]?.icon}{" "}
                                    {spaceTypeConfig[quote.spaceType]?.label ??
                                        "Unknown"}
                                </p>
                            </Col>
                        </Row>
                    </div>

                    {/* Date & time details */}
                    <div className="bg-light rounded-3 p-3 mb-4">
                        <p className={sectionLabel} style={sectionLabelStyle}>
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
                                {!isMultiDay && (
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
                                {!isMultiDay && (
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
                                            Multi-day booking — full days
                                            reserved.
                                        </span>
                                    </div>
                                </Col>
                            )}
                        </Row>
                    </div>

                    {/* Discount */}
                    <div className="bg-light rounded-3 p-3 mb-4">
                        <p className={sectionLabel} style={sectionLabelStyle}>
                            Discount Code
                        </p>
                        {discountApplied ? (
                            <div className="d-flex align-items-center gap-2">
                                <Badge
                                    bg="success"
                                    className="rounded-pill px-3 py-2"
                                >
                                    ✓ {discount} applied
                                </Badge>
                                <button
                                    className="btn btn-link text-muted p-0"
                                    style={{ fontSize: "0.85rem" }}
                                    onClick={() => {
                                        setDiscountApplied(false);
                                        setDiscount("");
                                    }}
                                >
                                    Remove
                                </button>
                            </div>
                        ) : (
                            <div className="d-flex gap-2">
                                <input
                                    className="form-control"
                                    placeholder="Enter discount code"
                                    value={discount}
                                    onChange={(e) =>
                                        setDiscount(e.target.value)
                                    }
                                    style={{ maxWidth: "260px" }}
                                />
                                <button
                                    className="btn btn-outline-primary fw-semibold"
                                    onClick={handleApplyDiscount}
                                    disabled={!discount.trim()}
                                >
                                    Apply
                                </button>
                            </div>
                        )}
                    </div>

                    {/* Price */}
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
                                Total Price
                            </p>
                            <p
                                className="text-muted mb-0"
                                style={{ fontSize: "0.8rem" }}
                            >
                                {discountApplied
                                    ? "Discount applied"
                                    : "No discount applied"}
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
                            className="btn btn-outline-danger px-4 py-2 fw-semibold"
                            onClick={handleCancel}
                            disabled={submitting}
                        >
                            Cancel
                        </button>
                        <button
                            className="btn btn-primary px-4 py-2 fw-semibold"
                            onClick={handleConfirm}
                            disabled={submitting}
                        >
                            {submitting ? (
                                <>
                                    <Spinner
                                        animation="border"
                                        size="sm"
                                        className="me-2"
                                    />
                                    Confirming...
                                </>
                            ) : (
                                "Reserve My Space"
                            )}
                        </button>
                    </div>
                </Card.Body>
            </Card>
        </div>
    );
}
