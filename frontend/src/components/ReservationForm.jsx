import { useState } from "react";

function ReservationForm() {
    const [form, setForm] = useState({
        date: "",
        time: "",
        spaceId: "",
        priceTypeId: "",
        contractId: "",
        carId: ""
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const res = await fetch("http://localhost:8080/api/reservations", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(form)
        });

        const data = await res.json();
        alert(data.message);
    };

    return (
        <form onSubmit={handleSubmit} className="mt-6 space-y-3">
            <input name="date" type="date" onChange={handleChange} />

            <input name="time" type="time" onChange={handleChange} />

            <input name="spaceId" placeholder="Space ID" onChange={handleChange} />
            <input name="priceTypeId" placeholder="Price Type ID" onChange={handleChange} />
            <input name="contractId" placeholder="Contract ID" onChange={handleChange} />

            <input name="carId" placeholder="Car ID" onChange={handleChange} />


            <button type="submit">Reserve</button>
        </form>
    );
}

export default ReservationForm;