const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export interface CreateProjectInput {
  name: string;
  description: string;
  githubRepositoryUrl: string;
}

export interface Project {
  id: string;
  name: string;
  description: string | null;
  githubRepository: string;
  createdAt: string;
}

export class ApiError extends Error {}

export async function createProject(input: CreateProjectInput): Promise<Project> {
  const response = await fetch(`${API_BASE_URL}/api/v1/projects`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(input),
  });

  if (!response.ok) {
    const errorBody = await response.json();
    throw new ApiError(errorBody.message ?? 'Não foi possível criar o projeto.');
  }

  return response.json();
}

export async function listProjects(): Promise<Project[]> {
  const response = await fetch(`${API_BASE_URL}/api/v1/projects`);

  if (!response.ok) {
    throw new ApiError('Não foi possível carregar os projetos.');
  }

  return response.json();
}