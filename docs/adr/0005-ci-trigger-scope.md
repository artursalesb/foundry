# ADR-0005: Pipeline de CI dispara em todo push, sem filtro de path

## Status
Aceito

## Contexto
Ao configurar o primeiro pipeline de CI (GitHub Actions), foi necessário decidir
o escopo de disparo: apenas push para `main`/Pull Requests, ou todo push em
qualquer branch. Também foi necessário decidir se os jobs de backend e frontend
deveriam rodar condicionalmente, apenas quando a pasta correspondente é alterada.

## Decisão
O pipeline dispara em push para qualquer branch. Os jobs de backend e frontend
rodam sempre, independentemente de qual pasta foi alterada no commit.

## Alternativas consideradas
- **Restringir a `main` e Pull Requests**: mais próximo de fluxo profissional real
  (gate de qualidade antes de merge), mas incompatível com o fluxo atual do projeto,
  onde commits vão direto para `main` sem branch/PR intermediário.
- **Filtro de path (`paths:`) por job**: rodaria só o job relevante à pasta alterada,
  economizando tempo de execução. Rejeitado por ora como otimização prematura — o
  tempo de execução atual é baixo o suficiente para não justificar a complexidade
  adicional na configuração do workflow.

## Consequências
**Positivas**
- Todo push recebe feedback imediato de ambos os lados do sistema.
- Configuração simples, fácil de entender lendo o arquivo YAML uma vez.

**Negativas**
- Mudanças isoladas em uma única pasta (ex: só frontend) ainda disparam o job da
  outra pasta, gastando minutos de execução de CI desnecessariamente.
- Reavaliar filtro de path e/ou restrição a `main`/PR quando o projeto adotar
  fluxo de branches, ou quando o tempo de execução do CI começar a incomodar.