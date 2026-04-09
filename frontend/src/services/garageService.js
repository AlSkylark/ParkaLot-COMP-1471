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
