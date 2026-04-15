import { deleteReq, get } from "./networkService";

export async function acceptQuote(quoteNo) {
    try {
        const res = get(`contracts/quote/${quoteNo}/accept`);
        return res;
    } catch (error) {
        console.error("Could not accept the reservation");
        return null;
    }
}

export async function getQuote(quoteNo) {
    try {
        const res = get(`contracts/quote/${quoteNo}`);
        return res;
    } catch (error) {
        console.error("Could not retrieve quote");
        return null;
    }
}

export async function cancelQuote(quoteNo) {
    try {
        const res = deleteReq(`contracts/quote/${quoteNo}`);
        return res;
    } catch (error) {
        console.error("Could not cancel the quote");
        return null;
    }
}
