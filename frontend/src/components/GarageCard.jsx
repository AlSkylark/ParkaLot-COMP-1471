import { useNavigate } from "react-router-dom";



function GarageCard({ image, title, description }) {
    const navigate = useNavigate();

    const handleBooking = () => {
        navigate("/booking"); 
    };

    return (
        <div className="Card">
            <img className="Card-img" src={image} alt={title} />
            <h2 className="Card-title">{title}</h2>
            <p className="Card-txt">{description}</p>
            <button className="book-btn" onClick={handleBooking}>
                Book Now
            </button>
        </div>
    );
}

export default GarageCard;
