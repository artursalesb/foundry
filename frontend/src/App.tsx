import { useEffect, useState } from 'react';
import { checkBackendHealth } from './services/healthCheckService';
import { CreateProjectForm } from './components/CreateProjectForm';
import { listProjects } from './services/projectService';
import type { Project } from './services/projectService';

type ConnectionStatus = 'checking' | 'connected' | 'error';

function App() {
  const [status, setStatus] = useState<ConnectionStatus>('checking');
  const [projects, setProjects] = useState<Project[]>([]);

  useEffect(() => {
    checkBackendHealth()
      .then(() => setStatus('connected'))
      .catch(() => setStatus('error'));
  }, []);

  useEffect(() => {
    listProjects()
      .then(setProjects)
      .catch(() => setProjects([]));
  }, []);

  function handleProjectCreated(project: Project) {
    setProjects((current) => [...current, project]);
  }

  return (
    <main>
      <h1>Foundry</h1>
      <p>Status da conexão com o backend: {status}</p>

      <CreateProjectForm onProjectCreated={handleProjectCreated} />

      <h2>Projetos</h2>
      <ul>
        {projects.map((project) => (
          <li key={project.id}>
            {project.name} — {project.githubRepository}
          </li>
        ))}
      </ul>
    </main>
  );
}

export default App;