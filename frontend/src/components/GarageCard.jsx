import { useNavigate } from "react-router-dom";

function GarageCard({ id, image, title, description }) {
    const navigate = useNavigate();

    return (
        <div className="card garage-card h-100">
            <img src={image} className="card-img-top" alt={title} />

            <div className="card-body">
                <h5 className="card-title">{title}</h5>
                <p className="card-text text-muted">{description}</p>
            </div>

            <div className="card-footer bg-white border-0 d-flex justify-content-between">


                <button
                    className="btn btn-primary"
                    onClick={() => navigate(`/reservation/${id}`)}
                >
                    Reserve
                </button>
            </div>
        </div>
    );
}

export default GarageCard;