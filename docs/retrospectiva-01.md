# Retrospectiva — Sprint 1

**Período:** 14/09 a 16/10 · **Projeto:** IMDConecta (projeto individual)

## 1. Fatos

**Entrega**
- A US1 (cadastro com e-mail institucional, 5 pontos) foi concluída de ponta a ponta: validador `@EmailInstitucional`, entidade `Usuario`, serviço com senha criptografada (BCrypt), endpoint `POST /api/usuarios` e CI no GitHub Actions (PR #10).
- Os 3 critérios de aceitação foram verificados com requisições reais: 201 para e-mail `@ufrn.edu.br`, 400 com mensagem clara para outro domínio e 409 para e-mail repetido.
- A US2, prevista como meta extra, não foi iniciada.

**Métricas de fluxo (US1)**

| Métrica | Valor |
|---|---|
| Lead time | ~37 dias (07/09 → 14/10) |
| Cycle time | ~3 dias (11/10 → 14/10) |
| Throughput | 1 item na sprint (5 pontos) |
| WIP | máximo 1 em "Em progresso" (limite respeitado) e no máximo 1 em "Em revisão" |
| Tempo em "Em revisão" | poucos minutos |

- Cerca de 34 dos 37 dias de lead time foram de espera, antes de o cartão entrar em "Em progresso".
- Pela Lei de Little, com WIP 1 e um item concluído a cada ~3 dias de trabalho, o cycle time ficou em ~3 dias (cycle time = WIP ÷ throughput).

**Eventos do processo**
- O trabalho de ambiente (Spring Boot, PostgreSQL, variáveis de ambiente) foi feito em 29 e 30/09 sem cartão no quadro, e a implementação da US1 só foi retomada em 11/10.
- Em 03/10 o prazo da Sprint 1 mudou de 02/10 para 16/10 (ajuste do cronograma).
- O requisito mudou durante a implementação: o domínio do e-mail passou de `@estudante.ufrn.br` (suposição da proposta) para `@ufrn.edu.br`, e a regra e a issue foram corrigidas.
- O `.env` quase foi commitado, porque o `.gitignore` gerado pelo Spring Initializr não o cobria. Foi detectado antes do push, e o `.env` entrou no `.gitignore`.

**Prática XP: TDD**
- O que funcionou: no validador e na entidade, o commit `test:` (falhando) veio antes do `feat:`. Os testes do validador já cobriam e-mail nulo e domínio parecido (`@ufrn.edu.br.com`). O CI roda os testes a cada push e pull request.
- O que não funcionou: o commit de teste do serviço de cadastro saiu com a classe de teste vazia, então o vermelho desse serviço não ficou registrado no histórico.

## 2. Causas (5 Porquês)

**Problema:** o cartão da US1 ficou ~34 dias parado antes de entrar em "Em progresso".

1. **Por quê?** Porque o trabalho de ambiente (29 e 30/09) não tinha cartão e, depois dele, a implementação só foi retomada em 11/10.
2. **Por que o trabalho de ambiente não tinha cartão?** Porque o backlog só tem histórias de usuário. Tarefas técnicas necessárias (setup, CI) não foram previstas e ficaram invisíveis no quadro.
3. **Por que a implementação só foi retomada em 11/10?** Porque, com o prazo ampliado em 03/10 para 16/10, o trabalho foi adiado sem um novo marco intermediário.
4. **Por que não havia marco intermediário?** Porque o planejamento da sprint fixou o escopo, mas não metas por semana, e o check-in semanal do acordo de processo não foi praticado.
5. **Por que o check-in não foi praticado?** Porque, em projeto individual, ninguém cobra a cerimônia e ela não tem dia e hora fixos.

**Causa raiz:** as cerimônias do acordo de processo dependem só da minha disciplina, sem horário marcado, e o quadro não refletia o trabalho real (tarefas técnicas sem cartão).

## 3. Ações

| # | Ação | Responsável | Prazo | Como verificar |
|---|---|---|---|---|
| 1 | Criar um cartão (com estimativa) para cada tarefa técnica antes de começá-la e ligar cada PR ao seu cartão com `Closes #n` | Cássio | A partir do Sprint Planning da Sprint 2 (04/11) | No fim da Sprint 2, todo PR integrado tem um cartão ligado |
| 2 | Marcar no calendário um check-in semanal de 10 min (segundas, antes da aula) para atualizar o quadro e as datas dos cartões | Cássio | Primeiro check-in em 26/10 | Pelo menos uma atualização do quadro por semana no histórico do Project |
| 3 | Replanejar o backlog para a Sprint 2, a última: estimar a US5, decidir quais histórias entram no MVP final e movê-las para o Sprint Backlog | Cássio | 26/10 | Sprint Backlog da Sprint 2 com todos os itens estimados |

## 4. Revisão do acordo de processo

- **Cadência:** as datas passam a seguir o cronograma de 03/10 (Sprint 1 de 14/09 a 16/10 e Sprint 2, final, de 01/11 a 30/11). A Sprint 3 e o bloco final deixaram de existir.
- **Definição de Pronto:** acrescentar "CI verde", já que o workflow existe.
- **Cerimônias:** o check-in semanal passa a ter dia e hora fixos (ação 2).
- **WIP:** mantidos os limites (Em progresso 1, Em revisão 2), porque o limite foi respeitado.