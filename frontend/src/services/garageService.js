import { get, post } from "./networkService";

export async function getGarage(id) {
    try {
        const res = await get(`garages/${id}`);
        return res;
    } catch (error) {
        console.error("Could not find garage");
        return null;
    }
}

export async function reserve(id, form) {
    try {
        const res = await post(`garages/${id}/reserve`, form);
        return res;
    } catch (error) {
        console.error("Could not process reservation");
        return null;
    }
}
