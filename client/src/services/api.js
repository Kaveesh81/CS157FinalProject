import axios from "axios";

const API_BASE_URL = "http://localhost:8081/mlservlet/api";
const CLIENT_BASE_URL = "http://localhost:5173";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      window.location.href = `${CLIENT_BASE_URL}/oauth2/authorization/google`;
    }
    return Promise.reject(error);
  }
);

export default api;
