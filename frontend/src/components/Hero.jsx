import { useNavigate } from "react-router-dom";

function Hero() {
  const navigate = useNavigate();

  return (
    <section className="text-center py-5">
      <h1>Book a Spot</h1>
      <p>Order your favorite parking spots online</p>
      <button
        className="btn btn-success"
        onClick={() => navigate("/booking")}
      >
        Book Now
      </button>
    </section>
  );
}

export default Hero;
