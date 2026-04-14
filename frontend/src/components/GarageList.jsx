import GarageCard from "./GarageCard";
import Porche911 from "../assets/Porche911.jpg";
import { useEffect, useState } from "react";
import { getAllGarages } from "../services/garageService";

function GarageList({ search = "" }) {
    const [garages, setGarages] = useState([]);

    useEffect(() => {
        async function fetchGarages() {
            const res = await getAllGarages();
            setGarages(res);
        }

        fetchGarages();
    }, []);

    const filteredGarages = garages.filter((garage) =>
        garage.name.toLowerCase().includes(search.toLowerCase()),
    );

    return (
        <div className="container mt-4">
            <div className="row">
                {filteredGarages.map((garage) => (
                    <div key={garage.id} className="col-md-4 col-lg-3 mb-4">
                        <GarageCard image={Porche911} {...garage} />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default GarageList;
