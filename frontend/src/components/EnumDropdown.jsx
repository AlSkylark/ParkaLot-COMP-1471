import { useEffect, useState } from "react";
import { getEnum } from "../services/enumService";

export default function EnumDropdown({ name, value, onChange }) {
    const [list, setList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function fetchEnum() {
            try {
                setLoading(true);
                const res = await getEnum(name);
                setList(res);
            } catch (err) {
                setError("Failed to load options.");
            } finally {
                setLoading(false);
            }
        }

        fetchEnum();
    }, [name]);

    return (
        <div>
            <select
                className="form-select"
                value={value}
                onChange={onChange}
                disabled={loading || !!error}
            >
                {loading && <option>Loading...</option>}
                {error && <option>{error}</option>}
                {!loading &&
                    !error && [
                        <option key="placeholder" value="">
                            Select an option...
                        </option>,
                        ...list.map((item) => (
                            <option key={item.id} value={item.id}>
                                {item.label}
                            </option>
                        )),
                    ]}
            </select>
        </div>
    );
}
