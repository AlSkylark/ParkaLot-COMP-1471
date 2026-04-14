import GarageCard from "./GarageCard";
import Porche911 from "../assets/Porche911.jpg";

function GarageList({ search = "" }) {

    const garages = [
        { id: 1, image: Porche911, title: "Teddington", description: "Secure and spacious parking." },
        { id: 2, image: Porche911, title: "Kingston", description: "Well-lit and accessible location." },
        { id: 3, image: Porche911, title: "Richmond", description: "CCTV monitored parking area." },
        { id: 4, image: Porche911, title: "Greenwich", description: "Close to city centre." },
        { id: 5, image: Porche911, title: "Lewisham", description: "Affordable and secure." },
        { id: 6, image: Porche911, title: "Stratford", description: "Near shopping centre." },
        { id: 7, image: Porche911, title: "Canary Wharf", description: "Premium parking location." },
        { id: 8, image: Porche911, title: "Woolwich", description: "24/7 access available." },
        { id: 9, image: Porche911, title: "Bromley", description: "Large parking capacity." },
        { id: 10, image: Porche911, title: "Croydon", description: "Secure underground parking." }
    ];

    // ✅ ADD THIS (this was missing)
    const filteredGarages = garages.filter((garage) =>
        garage.title.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="container mt-4">
            <div className="row">
                {filteredGarages.map((garage) => (
                    <div key={garage.id} className="col-md-4 col-lg-3 mb-4">
                        <GarageCard {...garage} />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default GarageList;