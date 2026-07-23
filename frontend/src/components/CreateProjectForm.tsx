import { useState } from 'react';
import type { FormEvent } from 'react';
import { createProject, ApiError } from '../services/projectService';
import type { Project } from '../services/projectService';
interface CreateProjectFormProps {
  onProjectCreated: (project: Project) => void;
}

export function CreateProjectForm({ onProjectCreated }: CreateProjectFormProps) {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [githubRepositoryUrl, setGithubRepositoryUrl] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    setError(null);
    setIsSubmitting(true);

    try {
      const project = await createProject({ name, description, githubRepositoryUrl });
      onProjectCreated(project);
      setName('');
      setDescription('');
      setGithubRepositoryUrl('');
    } catch (err) {
      const message = err instanceof ApiError ? err.message : 'Erro inesperado ao criar o projeto.';
      setError(message);
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Nome do projeto</label>
        <input
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="description">Descrição</label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="githubRepositoryUrl">Repositório GitHub</label>
        <input
          id="githubRepositoryUrl"
          value={githubRepositoryUrl}
          onChange={(e) => setGithubRepositoryUrl(e.target.value)}
          placeholder="owner/repo"
          required
        />
      </div>

      {error && <p role="alert">{error}</p>}

      <button type="submit" disabled={isSubmitting}>
        {isSubmitting ? 'Criando...' : 'Criar projeto'}
      </button>
    </form>
  );
}