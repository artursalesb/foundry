import { useEffect, useState } from 'react';
import { checkBackendHealth } from './services/healthCheckService';

type ConnectionStatus = 'checking' | 'connected' | 'error';

function App() {
  const [status, setStatus] = useState<ConnectionStatus>('checking');

  useEffect(() => {
    checkBackendHealth()
      .then(() => setStatus('connected'))
      .catch(() => setStatus('error'));
  }, []);

  return (
    <main>
      <h1>Foundry</h1>
      <p>Status da conexão com o backend: {status}</p>
    </main>
  );
}

export default App;