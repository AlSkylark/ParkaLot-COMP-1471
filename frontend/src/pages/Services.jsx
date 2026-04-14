function Services() {
    return (
        <div className="container mt-5">

            {/* HEADER */}
            <div className="text-center mb-5">
                <h1 className="fw-bold">Our Services</h1>
                <p className="text-muted">
                    Everything you need for smart and stress-free parking
                </p>
            </div>

            {/* SERVICES CARDS */}
            <div className="row g-4">

                <div className="col-md-4">
                    <div className="card h-100 shadow-sm p-3 text-center">
                        <h4>🔍 Search Parking</h4>
                        <p className="text-muted">
                            Easily find available parking spaces near your location.
                        </p>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="card h-100 shadow-sm p-3 text-center">
                        <h4>📅 Reservation</h4>
                        <p className="text-muted">
                            Book your parking spot in advance with ease.
                        </p>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="card h-100 shadow-sm p-3 text-center">
                        <h4>💳 Secure Payments</h4>
                        <p className="text-muted">
                            Safe and reliable payment and booking system.
                        </p>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="card h-100 shadow-sm p-3 text-center">
                        <h4>📍 Real-Time Availability</h4>
                        <p className="text-muted">
                            Get updated parking availability instantly.
                        </p>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="card h-100 shadow-sm p-3 text-center">
                        <h4>🚗 Multiple Vehicles</h4>
                        <p className="text-muted">
                            Manage and reserve parking for multiple cars.
                        </p>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="card h-100 shadow-sm p-3 text-center">
                        <h4>🔒 Secure System</h4>
                        <p className="text-muted">
                            Your data and reservations are protected.
                        </p>
                    </div>
                </div>

            </div>

        </div>
    );
}

export default Services;