import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:5000/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: 5000,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('bizpulse_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Services exported: authService, dashboardService, productService, insightService