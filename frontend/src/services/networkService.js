const getUrlBase = () => "/api";

export async function get(endpoint, params, request) {
  const res = await fetch(`${getUrlBase()}/${endpoint}`, request);
  if (!res.ok) throw new Error("GET failed");

  return res.json();
}

export async function post(endpoint, payload, params, request) {
  const res = await fetch(`${getUrlBase()}/${endpoint}`, {
    method: "POST",
    body: JSON.stringify(payload),
    headers: { "Content-Type": "application/json" },
    ...request,
  });
  if (!res.ok) throw new Error("POST failed");

  return res.json();
}
