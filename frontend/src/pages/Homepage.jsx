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
                        className="text-uppercase text-primary fw-semibold mb-2"
                        style={{ fontSize: "0.75rem", letterSpacing: "0.12em" }}
                    >
                        City-wide parking, simplified
                    </p>
                    <h1
                        className="fw-bold text-dark mb-3"
                        style={{ fontSize: "3.5rem", letterSpacing: "-0.02em" }}
                    >
                        Parka<span className="text-primary">Lot</span>
                    </h1>
                    <p
                        className="text-muted mx-auto mb-4"
                        style={{ maxWidth: "420px" }}
                    >
                        Find and reserve your perfect parking spot across our
                        network of secure, city-centre garages.
                    </p>
                </div>
            </div>

            {/* FEATURE SECTION */}
            <section className="container text-center mt-2">
                <h2 className="fw-bold mb-4">Why Choose ParkaLot?</h2>

                <div className="row">
                    <div className="col-md-4">
                        <h5>🔍 Easy Search</h5>
                        <p>Find available parking spaces instantly near you.</p>
                    </div>

                    <div className="col-md-4">
                        <h5>⚡ Fast Booking</h5>
                        <p>Reserve your parking space in seconds.</p>
                    </div>

                    <div className="col-md-4">
                        <h5>🔒 Secure</h5>
                        <p>Your bookings and data are safe and protected.</p>
                    </div>
                </div>
            </section>

            {/* SEARCH SECTION */}
            <section className="container text-center mt-2">
                <h2 className="text-center fw-bold mb-4">Find Parking</h2>

                <input
                    className="container-lg form-control"
                    placeholder="Search location..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </section>

            {/* GARAGE LIST (YOUR EXISTING COMPONENT) */}
            <section className="container">
                <h2 className="fw-bold mb-4 text-center">Available Garages</h2>
                <GarageList search={search} />
            </section>

            {/* HOW IT WORKS */}
            <section className="bg-light py-5 mt-5">
                <div className="container text-center">
                    <h2 className="fw-bold mb-4">How It Works</h2>

                    <div className="row">
                        <div className="col-md-4">
                            <h5>1. Search</h5>
                            <p>Browse available garages.</p>
                        </div>

                        <div className="col-md-4">
                            <h5>2. Select</h5>
                            <p>Choose the best parking option.</p>
                        </div>

                        <div className="col-md-4">
                            <h5>3. Reserve</h5>
                            <p>Book instantly and secure your spot.</p>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default Home;
