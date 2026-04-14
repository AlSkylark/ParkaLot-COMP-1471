function About() {
    return (
        <div className="container mt-5">

            {/* HEADER */}
            <div className="text-center mb-5">
                <h1 className="fw-bold">About ParkaLot</h1>
                <p className="text-muted">
                    Smart parking made simple and efficient.
                </p>
            </div>

            {/* CONTENT */}
            <div className="row align-items-center">

                <div className="col-md-6">
                    <h4 className="fw-bold mb-3">Our Mission</h4>
                    <p className="text-secondary">
                        ParkaLot is designed to simplify the way people find and reserve
                        parking spaces. We aim to reduce time wasted searching for parking
                        and provide a seamless digital experience.
                    </p>

                    <h4 className="fw-bold mt-4 mb-3">What We Offer</h4>
                    <ul className="text-secondary">
                        <li>Real-time parking availability</li>
                        <li>Easy reservation system</li>
                        <li>Secure and reliable bookings</li>
                        <li>User-friendly interface</li>
                    </ul>
                </div>

                <div className="col-md-6 text-center">
                    <img
                        src="https://images.unsplash.com/photo-1503376780353-7e6692767b70"
                        alt="parking"
                        className="img-fluid rounded shadow"
                    />
                </div>

            </div>

        </div>
    );
}

export default About;