import { checkedSignedIn } from "../services/customerService";

function Footer() {
    return (
        <footer className="bg-success text-white pt-4 pb-2 mt-auto">
            <div className="container">
                <div className="row text-center text-md-start">
                    <div className="col-md-4 mb-3">
                        <h5 className="fw-bold">ParkaLot</h5>
                        <p className="small">
                            Book your parking spot quickly and securely.
                        </p>
                    </div>

                    {/* Quick Links */}
                    <div className="col-md-4 mb-3">
                        <h6 className="fw-bold">Quick Links</h6>
                        <ul className="list-unstyled">
                            <li>
                                <a
                                    href="/"
                                    className="text-white text-decoration-none"
                                >
                                    Home
                                </a>
                            </li>
                            <li>
                                <a
                                    href="/#/about"
                                    className="text-white text-decoration-none"
                                >
                                    About
                                </a>
                            </li>
                            <li>
                                <a
                                    href="/#/services"
                                    className="text-white text-decoration-none"
                                >
                                    Services
                                </a>
                            </li>
                            {checkedSignedIn() ? (
                                <li>
                                    <a
                                        href="/#/profile"
                                        className="text-white text-decoration-none"
                                    >
                                        Profile
                                    </a>
                                </li>
                            ) : (
                                <li>
                                    <a
                                        href="/#/login"
                                        className="text-white text-decoration-none"
                                    >
                                        Login
                                    </a>
                                </li>
                            )}
                        </ul>
                    </div>

                    {/* Contact */}
                    <div className="col-md-4 mb-3">
                        <h6 className="fw-bold">Contact</h6>
                        <p className="small mb-1">support@parkalot.com</p>
                        <p className="small">London, UK</p>
                    </div>
                </div>

                <hr className="border-secondary" />

                <p className="text-center small mb-0">
                    © 2026 ParkaLot. All rights reserved.
                </p>
            </div>
        </footer>
    );
}

export default Footer;
