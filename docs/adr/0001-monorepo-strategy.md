# ADR-0001: Adotar monorepo para backend e frontend

## Status
Aceito

## Contexto
O Foundry é composto por um backend (Java/Spring Boot) e um frontend (React/TypeScript),
desenvolvidos por um único desenvolvedor durante a fase de MVP. As duas camadas evoluirão
em conjunto, especialmente no início, onde contratos de API e telas mudam com frequência.

## Decisão
Utilizar um único repositório Git contendo `backend/`, `frontend/` e `docs/`, em vez de
repositórios separados para cada camada.

## Alternativas consideradas
- **Multi-repo** (um repositório para backend, outro para frontend): rejeitado nesta fase
  por adicionar overhead de sincronização (versionamento cruzado, PRs coordenados) sem
  benefício real para um time de uma pessoa.

## Consequências
**Positivas**
- Um único PR pode alterar API e client simultaneamente, mantendo consistência.
- Histórico de commits único facilita rastrear a evolução do produto como um todo.
- Simplifica o setup inicial de CI/CD (um pipeline, com jobs condicionais por pasta).

**Negativas**
- Caso o projeto cresça para múltiplos times, será necessário introduzir path filters no
  CI e possivelmente CODEOWNERS por diretório, para evitar builds desnecessários e
  delimitar responsabilidade.
- Esta decisão será revisitada caso o projeto atinja escala que justifique repositórios
  independentes por serviço (ex: extração de um serviço de análise separado).