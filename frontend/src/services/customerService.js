import { get, post } from "./networkService";

export async function getProfile() {
  const endpoint = "profile";
  const token = localStorage.getItem("auth-token");
  try {
    const res = await get(endpoint, null, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return res;
  } catch (error) {
    console.error("No profile found or token out of date, signing out");
    signout();
  }
}

export async function signin(email) {
  const endpoint = "signin";
  try {
    const res = await post(endpoint, { email: email });
    localStorage.setItem("auth-token", res.token);
  } catch (error) {
    console.error("Login unsuccessful.");
    return false;
  }

  return true;
}

export function signout() {
  localStorage.removeItem("auth-token");
}

export function checkedSignedIn() {
  const token = localStorage.getItem("auth-token");

  return !!token;
}
