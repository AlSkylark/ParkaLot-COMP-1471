import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function Footer() {
    const { isLoggedIn } = useAuth();
    const year = new Date().getFullYear();

    const footerLink = (to, label) => (
        <li className="mb-1">
            <Link
                to={to}
                className="text-decoration-none fw-semibold"
                style={{ color: "rgba(255,255,255,0.65)", fontSize: "0.9rem" }}
            >
                {label}
            </Link>
        </li>
    );

    return (
        <footer
            style={{ backgroundColor: "#0f2d1f" }}
            className="text-white pt-5 pb-3 mt-auto"
        >
            <div className="container">
                <div className="row text-center text-md-start g-4 mb-4">
                    {/* Brand */}
                    <div className="col-md-4">
                        <h5
                            className="fw-bold fs-4 mb-2"
                            style={{ letterSpacing: "-0.02em" }}
                        >
                            Parka<span style={{ color: "#22c55e" }}>Lot</span>
                        </h5>
                        <p
                            style={{
                                color: "rgba(255,255,255,0.55)",
                                fontSize: "0.9rem",
                                lineHeight: "1.6",
                            }}
                        >
                            Find and reserve your perfect parking spot across
                            our network of secure, city-centre garages.
                        </p>
                    </div>

                    {/* Quick links */}
                    <div className="col-md-4">
                        <h6
                            className="fw-bold text-uppercase mb-3"
                            style={{
                                fontSize: "0.7rem",
                                letterSpacing: "0.08em",
                                color: "rgba(255,255,255,0.4)",
                            }}
                        >
                            Quick Links
                        </h6>
                        <ul className="list-unstyled mb-0">
                            {footerLink("/", "Home")}
                            {footerLink("/about", "About")}
                            {footerLink("/services", "Services")}
                            {isLoggedIn
                                ? footerLink("/profile", "My Profile")
                                : footerLink("/login", "Login")}
                        </ul>
                    </div>

                    {/* Contact */}
                    <div className="col-md-4">
                        <h6
                            className="fw-bold text-uppercase mb-3"
                            style={{
                                fontSize: "0.7rem",
                                letterSpacing: "0.08em",
                                color: "rgba(255,255,255,0.4)",
                            }}
                        >
                            Contact
                        </h6>
                        <ul className="list-unstyled mb-0">
                            <li className="mb-1">
                                <a
                                    href="mailto:support@parkalot.com"
                                    className="text-decoration-none fw-semibold"
                                    style={{
                                        color: "rgba(255,255,255,0.65)",
                                        fontSize: "0.9rem",
                                    }}
                                >
                                    ✉️ support@parkalot.com
                                </a>
                            </li>
                            <li>
                                <span
                                    style={{
                                        color: "rgba(255,255,255,0.65)",
                                        fontSize: "0.9rem",
                                    }}
                                >
                                    📍 London, UK
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>

                <hr style={{ borderColor: "rgba(255,255,255,0.1)" }} />

                <p
                    className="text-center mb-0"
                    style={{
                        color: "rgba(255,255,255,0.35)",
                        fontSize: "0.8rem",
                    }}
                >
                    © {year} ParkaLot. All rights reserved.
                </p>
            </div>
        </footer>
    );
}

export default Footer;
