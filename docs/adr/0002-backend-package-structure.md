# ADR-0002: Organizar o backend por camada (package-by-layer)

## Status
Aceito

## Contexto
O backend precisa de uma estrutura de pacotes que reflita a separação de responsabilidades
da Clean Architecture (domínio, aplicação, infraestrutura, interface) desde o primeiro commit,
evitando que regras de negócio nasçam acopladas a detalhes de framework.

## Decisão
Organizar os pacotes por camada arquitetural (`domain`, `application`, `infrastructure`,
`interfaces`) em vez de por feature/contexto de negócio.

## Alternativas consideradas
- **Package-by-feature**: agruparia código por contexto de negócio (ex: `rules/`, `backlog/`),
  cada um com suas próprias subcamadas internas. Rejeitado nesta fase por ser otimização
  prematura — o domínio ainda não tem contextos delimitados o suficiente para justificar essa
  divisão, e isso pode ser migrado depois sem reescrever regra de negócio, só reorganizando
  pacotes.

## Consequências
**Positivas**
- Fronteiras da Clean Architecture ficam explícitas na própria árvore de diretórios.
- Facilita revisão de dependências indevidas "a olho" enquanto o projeto é pequeno.

**Negativas**
- Conforme o número de contextos de negócio crescer (Rules, Engine, Backlog, integração
  GitHub), cada camada pode acumular muitos arquivos não relacionados entre si.
- Esta decisão será revisitada quando o domínio justificar migração para package-by-feature,
  possivelmente reforçada por testes de arquitetura (ex: ArchUnit) para impedir violação de
  camada de forma automatizada.