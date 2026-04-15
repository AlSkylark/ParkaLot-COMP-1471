import { useState } from "react";
import { useNavigate } from "react-router-dom";
import GarageList from "../components/GarageList";

function Home() {
    const navigate = useNavigate();
    const [search, setSearch] = useState("");

    return (
        <>
            {/* Hero */}
            <div className="bg-white border-bottom py-5">
                <div className="container text-center py-3">
                    <p
                        className="text-uppercase fw-semibold mb-3"
                        style={{
                            fontSize: "0.75rem",
                            letterSpacing: "0.12em",
                            color: "#0f2d1f",
                        }}
                    >
                        City-wide parking, simplified
                    </p>
                    <h1
                        className="fw-bold text-dark mb-4"
                        style={{ fontSize: "3.5rem", letterSpacing: "-0.02em" }}
                    >
                        Parka<span style={{ color: "#22c55e" }}>Lot</span>
                    </h1>
                    <p
                        className="text-muted mx-auto mb-0"
                        style={{ maxWidth: "420px" }}
                    >
                        Find and reserve your perfect parking spot across our
                        network of secure, city-centre garages.
                    </p>
                </div>
            </div>

            {/* Feature section */}
            <section className="container text-center my-6 py-5">
                <h2 className="fw-bold mb-5">Why Choose ParkaLot?</h2>
                <div className="row g-4">
                    <div className="col-md-4">
                        <h5>🔍 Easy Search</h5>
                        <p className="text-muted">
                            Find available parking spaces instantly near you.
                        </p>
                    </div>
                    <div className="col-md-4">
                        <h5>⚡ Fast Booking</h5>
                        <p className="text-muted">
                            Reserve your parking space in seconds.
                        </p>
                    </div>
                    <div className="col-md-4">
                        <h5>🔒 Secure</h5>
                        <p className="text-muted">
                            Your bookings and data are safe and protected.
                        </p>
                    </div>
                </div>
            </section>

            <hr className="container" />

            {/* Search section */}
            <section className="container text-center py-5">
                <h2 className="fw-bold mb-4">Find Parking</h2>
                <input
                    className="form-control form-control-lg mx-auto"
                    placeholder="Search location..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    style={{ maxWidth: "560px" }}
                />
            </section>

            {/* Garage list */}
            <section className="container pb-5">
                <h2 className="fw-bold mb-4 text-center">Available Garages</h2>
                <GarageList search={search} />
            </section>

            {/* How it works */}
            <section className="bg-light py-5 mt-2 mb-0">
                <div className="container text-center py-3">
                    <h2 className="fw-bold mb-5">How It Works</h2>
                    <div className="row g-4">
                        <div className="col-md-4">
                            <h5>1. Search</h5>
                            <p className="text-muted">
                                Browse available garages.
                            </p>
                        </div>
                        <div className="col-md-4">
                            <h5>2. Select</h5>
                            <p className="text-muted">
                                Choose your favorite garage.
                            </p>
                        </div>
                        <div className="col-md-4">
                            <h5>3. Reserve</h5>
                            <p className="text-muted">
                                Book instantly and secure your spot.
                            </p>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default Home;
