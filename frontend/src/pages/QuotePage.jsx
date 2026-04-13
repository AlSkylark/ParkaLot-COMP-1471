import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

function QuotePage() {
  const { state } = useLocation();
  const navigate = useNavigate();

  const [accepted, setAccepted] = useState(false);

  if (!state) return <p>No quote available</p>;

  return (
    <div className="quote-container">
      <h2>Quote Contract</h2>

      <div className="quote-box">
        <p><strong>Location:</strong> {state.location}</p>
        <p><strong>Duration:</strong> {state.hours} hours</p>
        <p><strong>Total Price:</strong> £{state.price}</p>
      </div>


      <div className="terms">
        <input
          type="checkbox"
          id="terms"
          onChange={(e) => setAccepted(e.target.checked)}
        />
        <label htmlFor="terms">
          I understand that by clicking Reserve, I agree to the terms & conditions.
        </label>
      </div>


      <button
        disabled={!accepted}
        onClick={() => navigate("/confirm")}
        className="reserve-btn"
      >
        Reserve
      </button>
    </div>
  );
}

export default QuotePage;
