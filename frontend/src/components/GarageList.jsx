import GarageCard from "./GarageCard"; 
import Porche911 from "../assets/Porche911.jpg";

function GarageList() {

    const garages = [
        {
            id: 1,
            image: Porche911,
            title: "Teddington",
            description: "A car park is a designated area where vehicles can be safely parked."
        },
        {
            id: 2,
            image: Porche911,
            title: "Kingston",
            description: "Secure parking with easy access and good lighting."
        },
        {
            id: 3,
            image: Porche911,
            title: "Richmond",
            description: "Spacious parking with CCTV monitoring."
        }
    ];

    return (
        <div className="container">
            {garages.map((garage) => (
                <GarageCard key={garage.id} {...garage} />
            ))}
        </div>
    );
}

export default GarageList;
