import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import { CreateProjectForm } from './CreateProjectForm';
import { createProject, ApiError } from '../services/projectService';

vi.mock('../services/projectService', async () => {
  const actual = await vi.importActual('../services/projectService');
  return {
    ...actual,
    createProject: vi.fn(),
  };
});

const mockedCreateProject = vi.mocked(createProject);

describe('CreateProjectForm', () => {
  beforeEach(() => {
    mockedCreateProject.mockReset();
  });

  it('calls onProjectCreated with the created project on success', async () => {
    const user = userEvent.setup();
    const onProjectCreated = vi.fn();
    const createdProject = {
      id: '1',
      name: 'Foundry',
      description: 'Backlog de evolução',
      githubRepository: 'foundry-org/foundry',
      createdAt: new Date().toISOString(),
    };
    mockedCreateProject.mockResolvedValueOnce(createdProject);

    render(<CreateProjectForm onProjectCreated={onProjectCreated} />);

    await user.type(screen.getByLabelText(/nome do projeto/i), 'Foundry');
    await user.type(screen.getByLabelText(/repositório github/i), 'foundry-org/foundry');
    await user.click(screen.getByRole('button', { name: /criar projeto/i }));

    await waitFor(() => {
      expect(onProjectCreated).toHaveBeenCalledWith(createdProject);
    });
  });

  it('shows an error message when the API rejects the request', async () => {
    const user = userEvent.setup();
    mockedCreateProject.mockRejectedValueOnce(
      new ApiError('Formato de repositório inválido.')
    );

    render(<CreateProjectForm onProjectCreated={vi.fn()} />);

    await user.type(screen.getByLabelText(/nome do projeto/i), 'Foundry');
    await user.type(screen.getByLabelText(/repositório github/i), 'invalido');
    await user.click(screen.getByRole('button', { name: /criar projeto/i }));

    expect(await screen.findByRole('alert')).toHaveTextContent(
      'Formato de repositório inválido.'
    );
  });
});