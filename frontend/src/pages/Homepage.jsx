import GarageList from "../components/GarageList";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

function Home() {
    const [search, setSearch] = useState("");

    const navigate = useNavigate();

    return (
        <>
            {/* FEATURE SECTION */}
            <section className="container text-center mt-5">
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

            {/* CALL TO ACTION */}
            <section className="text-center py-5">
                <h3 className="mb-3">Start Parking Smarter Today</h3>

                <button
                    className="btn btn-primary btn-lg"
                    onClick={() => navigate("/garage")}
                >
                    Find Parking Now
                </button>
            </section>
        </>
    );
}

export default Home;
