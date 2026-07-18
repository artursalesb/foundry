# Foundry

> Uma plataforma que analisa a estrutura real de um repositório e transforma lacunas de
> engenharia (testes, CI/CD, containerização, documentação) em um backlog de evolução
> priorizado e acionável — não apenas mais um gerenciador de tarefas.

## Status do projeto

🚧 Em desenvolvimento — fase de planejamento arquitetural concluída, implementação iniciada.

## Stack

- **Backend**: Java 21, Spring Boot, Maven
- **Frontend**: React, TypeScript, Vite
- **Banco de dados**: PostgreSQL
- **Infraestrutura**: Docker (planejado), GitHub Actions (planejado)

## Estrutura do repositório´

backend/     # API e regras de negócio (Java / Spring Boot)
frontend/    # Interface (React / TypeScript)
docs/adr/    # Registros de decisões arquiteturais (Architecture Decision Records)

## Decisões arquiteturais

Todas as decisões técnicas relevantes são documentadas em [`docs/adr/`](./docs/adr/),
seguindo o formato de Architecture Decision Records.

## Como rodar o projeto

_Em breve — o setup local será documentado a partir do commit de bootstrap do backend._