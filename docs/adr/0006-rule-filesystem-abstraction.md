# ADR-0006: Rules operam sobre caminho de sistema de arquivos, não sobre GitHub diretamente

## Status
Aceito

## Contexto
Ao introduzir o primeiro detector de lacunas de engenharia (Rule), foi necessário decidir
se ele deveria operar diretamente contra a API do GitHub/repositório remoto, ou contra uma
abstração mais simples.

## Decisão
`Rule` opera sobre um `java.nio.file.Path`, representando uma cópia local (real ou
temporária) do conteúdo do repositório. A responsabilidade de obter esse conteúdo —
seja por clone via Git, seja por leitura remota via API do GitHub — é deferida para uma
camada de infraestrutura futura, ainda não implementada.

## Alternativas consideradas
- **Rule operando diretamente sobre a API do GitHub**: acoplaria a lógica de detecção a
  uma forma específica de acesso a repositório, e tornaria testes de unidade dependentes
  de mocks de API HTTP em vez de arquivos reais em disco. Rejeitado por complicar testes
  e por antecipar uma decisão de infraestrutura (clone vs. API remota) que ainda não
  precisa ser tomada.

## Consequências
**Positivas**
- Rules são testáveis com diretórios temporários reais, sem qualquer dependência de rede.
- A decisão entre clonar o repositório ou ler via API do GitHub fica isolada e adiável,
  sem impactar a lógica de detecção já escrita.

**Negativas**
- Um componente de infraestrutura ainda precisa ser construído para popular esse `Path`
  a partir de um repositório GitHub real — este ADR não resolve isso, apenas isola o
  problema para ser resolvido depois.