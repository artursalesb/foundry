# Foundry

![CI](https://github.com/artursalesb/foundry/actions/workflows/ci.yml/badge.svg)

> Uma plataforma que analisa a estrutura real de um repositório e transforma lacunas de
> engenharia (testes, CI/CD, containerização, documentação) em um backlog de evolução
> priorizado e acionável — não apenas mais um gerenciador de tarefas.

## Por que o Foundry existe

Ferramentas como Jira, Trello e Linear tratam tarefas como dados isolados, desconectados
do estado real do código. O Foundry inverte essa lógica: usa a estrutura real do
repositório (via GitHub) como fonte de verdade, e transforma lacunas objetivas de
engenharia — ausência de testes, CI, containerização, documentação — em um plano de
evolução priorizado.

Não é um clone de ferramenta de gestão de projeto. É uma ferramenta de diagnóstico e
evolução de engenharia de software.

## Status do projeto

🚧 Em desenvolvimento ativo. Já implementado:
- Criação e listagem de projetos, com repositório GitHub associado
- Arquitetura em camadas (Clean Architecture), com testes automatizados end-to-end
- Pipeline de CI rodando testes de backend e frontend a cada push

Em construção: motor de detecção de lacunas de engenharia e geração de backlog.

<!-- TODO: adicionar GIF ou screenshot demonstrando o fluxo de criação de projeto -->

## Stack

- **Backend**: Java 21, Spring Boot, Maven
- **Frontend**: React, TypeScript, Vite
- **Testes**: JUnit 5 + AssertJ (backend), Vitest + Testing Library (frontend)
- **Banco de dados**: PostgreSQL (planejado — atualmente persistência em memória)
- **CI/CD**: GitHub Actions

## Como rodar localmente

### Backend
```bash
cd backend
./mvnw spring-boot:run
```
API disponível em `http://localhost:8080`.

### Frontend
```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```
Aplicação disponível em `http://localhost:5173`.

### Rodando os testes
```bash
# Backend
cd backend && ./mvnw test

# Frontend
cd frontend && npm test
```

## Estrutura do repositório
backend/ # API e regras de negócio (Java / Spring Boot, Clean Architecture)
frontend/ # Interface (React / TypeScript)
docs/adr/ # Registros de decisões arquiteturais (Architecture Decision Records)


## Decisões arquiteturais

Todas as decisões técnicas relevantes — e os trade-offs considerados em cada uma — estão
documentadas em [`docs/adr/`](./docs/adr/), seguindo o formato de Architecture Decision
Records. Vale a leitura para entender o raciocínio por trás da arquitetura, não só o
resultado final.