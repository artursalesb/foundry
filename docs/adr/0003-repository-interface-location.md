# ADR-0003: Interfaces de repositório vivem na camada de domínio

## Status
Aceito

## Contexto
Ao introduzir o primeiro caso de uso (criação de Project), foi necessário decidir
onde declarar a interface responsável por persistir e recuperar entidades: na camada
de domínio ou na camada de aplicação.

## Decisão
Interfaces de repositório (ex: `ProjectRepository`) são declaradas dentro do pacote
de domínio da entidade correspondente (`domain/project/`). As implementações
concretas vivem em `infrastructure/persistence/`, seguindo Dependency Inversion —
a camada de aplicação depende apenas da interface, nunca da implementação.

## Alternativas consideradas
- **Interface em `application/`**: também é uma posição defensável (comum em alguns
  materiais de Clean Architecture), já que é a aplicação quem "usa" o repositório.
  Rejeitada aqui por uma preferência de modelagem: o conceito de "um Project pode ser
  salvo e recuperado" é parte da linguagem ubíqua do domínio, não uma decisão de
  orquestração de caso de uso específico.

## Consequências
**Positivas**
- Múltiplas implementações (em memória, PostgreSQL, H2) podem coexistir e ser
  trocadas via configuração do Spring, sem alterar caso de uso nem domínio.
- Reforça que a direção de dependência aponta sempre para dentro (infrastructure
  depende de domain, nunca o contrário).

**Negativas**
- Nenhuma identificada até o momento. Reavaliar se o domínio crescer a ponto de
  interfaces de persistência acumularem métodos irrelevantes para o domínio em si.