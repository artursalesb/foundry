const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function checkBackendHealth(): Promise<string> {
  const response = await fetch(`${API_BASE_URL}/api/v1/ping`);

  if (!response.ok) {
    throw new Error(`Backend respondeu com status ${response.status}`);
  }

  return response.text();
}