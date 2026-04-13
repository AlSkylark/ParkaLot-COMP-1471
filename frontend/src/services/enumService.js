import { get } from "./networkService";

/**
 * A simple service that fetches enums from the backend
 * @param {string} name
 * @returns A list of DropdownItem with id and label
 */
export async function getEnum(name) {
    const baseEndpoint = "enums/";

    try {
        const res = await get(baseEndpoint + name);
        return res;
    } catch (error) {
        console.error("Could not find enum");
        return [];
    }
}
