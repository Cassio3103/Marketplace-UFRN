# Proposta — IMDConecta

**Disciplina:** DIM0510 — Processos de Software
**Repositório:** Marketplace-UFRN

---

## 1. Visão do produto

Estudantes de graduação da UFRN frequentemente precisam de apoio pontual em disciplinas (tirar dúvida, aula de reforço) ou de serviços acadêmicos (revisão de texto, formatação ABNT, design para TCC), mas a oferta de monitoria formal é escassa — vagas limitadas, processo por edital, cobertura parcial das disciplinas. O mercado informal que resolve isso hoje é disperso em grupos de WhatsApp por turma, sem estrutura de busca, sem alcance entre turmas diferentes que cursam a mesma disciplina.

O **IMDConecta** é um marketplace peer-to-peer que conecta estudantes da UFRN que oferecem e buscam monitoria/aulas particulares e freelas acadêmicos, com busca filtrada por disciplina e por categoria de habilidade — permitindo que a demanda e a oferta se encontrem independentemente do curso de origem, já que várias disciplinas (Cálculo, Física, Programação) são compartilhadas entre cursos diferentes.

**Público-alvo:** estudantes de graduação da UFRN (validado via e-mail institucional).

**Problema específico:** ausência de um canal estruturado e buscável para encontrar apoio acadêmico pontual entre pares, fora do processo formal de monitoria.

**Evidência:** _(preencher com resultado do formulário de validação, se/quando aplicado)._ _Para a evidência, farei um formulário de pesquisa para saber se os alunos concordariam com um sistema como esse na escala da UFRN._

---

## 2. Definição do MVP

**Hipótese de valor:** Acredito que estudantes da UFRN vão usar uma plataforma para buscar e oferecer monitoria e freelas acadêmicos entre pares porque isso resolve uma lacuna que o WhatsApp e a monitoria formal não cobrem — busca estruturada por disciplina/habilidade, dentro de uma comunidade de confiança.

**Dentro do MVP:**
- Cadastro com e-mail institucional (@estudante.ufrn.br / @imd.ufrn.br)
- Criar anúncio (título, disciplina ou categoria de habilidade, descrição, preço, disponibilidade)
- Busca com filtro por disciplina e por categoria de habilidade
- Contato direto via link externo (ex: WhatsApp)
- Perfil simples do usuário (nome, curso, período)
- Tipos de trabalhos a serem executados e vendidos entre alunos

**Fora do MVP (explicitamente):**
- Pagamento integrado na plataforma
- Chat interno
- Sistema de avaliação/reputação
- Outras categorias (venda de material, carona)

**Critérios de "pronto" do MVP:** um estudante autenticado consegue publicar um anúncio de monitoria ou freela, e outro estudante consegue encontrá-lo via busca filtrada e entrar em contato, sem intervenção manual.

**Viabilidade:** escopo desenhado para ser entregável por 1 desenvolvedor em 4 sprints.

---

## 3. Backlog e quadro Kanban

- Quadro Kanban/_Backlog_: _https://github.com/users/Cassio3103/projects/5_

---

## 4. Stack técnica e justificativa

- **Back-end:** Java + Spring Boot — ecossistema maduro, documentação extensa, forte suporte a APIs REST e persistência de dados (JPA/Hibernate), facilitando a construção rápida do domínio de anúncios/usuários.
- **Front-end:** Vue.js — curva de aprendizado mais suave que outras opções JS (sintaxe de template próxima do HTML), adequado ao contexto de desenvolvimento solo com prazo curto. A comunicação com o back-end ocorre via API REST, tornando a escolha do framework front-end independente da lógica de negócio.

---

## 5. Acordo de processo

- **Cadência:** _Sprints de 3 semanas:_
    - Sprint 1: 14/09 a 02/10
    - Sprint 2: 05/10 a 23/10
    - Sprint 3: 26/10 a 20/11
    - Bloco final: 23/11 a 11/12
    - Planejamento: início de cada sprint (primeira segunda-feira do período)
    - Fechamento: sexta-feira da semana de entrega, até 23:59 (data oficial de entrega)
- **Cerimônias:** 
    - Sprint Planning (20-30 min, início da sprint): selecionar itens do backlog pra sprint
    - Check-in (10 min, semanal — dá pra alinhar com as aulas de segunda/quarta): atualizar o quadro Kanban, revisar progresso
    - Sprint Review (20-30 min, antes da apresentação): validar entregas contra critérios de aceite, preparar a apresentação
    - Sprint Retrospective (15 min, logo após a apresentação): registrar o que funcionou/travou pra ajustar a sprint seguinte
- **Definição de Pronto (DoD):** 
    -Funcionalidade implementada e testada manualmente
    - Código commitado no repositório com mensagem clara
    - Sem erros bloqueantes conhecidos
    - Critério de aceitação da user story atendido
- **Papéis:** projeto individual — Cássio acumula os papéis de Product Owner e desenvolvedor.
- **Ferramentas:** GitHub + GitHub Projects (backlog e Kanban).
- **WIP limits:** 
    - Sprint Backlog: sem limite
    - Em progresso: máx. 1
    - Em revisão: máx. 2
    - Pronto: sem limite

---

## 6. Equipe

| Nome | Matrícula | Usuário GitHub |
|---|---|---|
| _Cássio_ | _20240058085_ | _Cassio3103_ |

**Coorte de apresentação:** _[preencher]_

---

## 7. Integração com outras disciplinas

_"Não há integração"._