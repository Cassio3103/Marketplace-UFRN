# Marketplace-UFRN

Marketplace peer-to-peer para estudantes da UFRN oferecerem e buscarem monitorias, aulas particulares e freelas acadêmicos (revisão de texto, design, TCC, projetos), com busca filtrada por disciplina e categoria de habilidade.

Projeto desenvolvido para a disciplina **DIM0510 — Processos de Software**, com entregas em estilo Scrum (Sprints).

## Equipe

**Coorte de apresentação:** _B_

| Nome | Matrícula | Usuário GitHub |
|---|---|---|
| _Cássio Felipe Paiva Ferreira da Silva_ | _20240058085_ | _@Cassio3103_ |


## Integração com outras disciplinas

_Não há integração._

## Documentação

- Proposta completa: [`docs/proposta.md`](docs/proposta.md)
- Backlog e quadro Kanban:  _https://github.com/users/Cassio3103/projects/5_
- Vídeo de apresentação: _(https://youtu.be/tTVIQaBPh2k)_

## Como executar o backend

### Pré-requisitos
- Java 21
- PostgreSQL

### 1. Criar o banco e o usuário

```bash
sudo -u postgres psql
```

```sql
CREATE DATABASE imdconecta;
CREATE USER imdconecta_user WITH PASSWORD 'senha_exemplo';
GRANT ALL PRIVILEGES ON DATABASE imdconecta TO imdconecta_user;
\c imdconecta
GRANT ALL ON SCHEMA public TO imdconecta_user;
\q
```

As tabelas são criadas automaticamente pelo Hibernate quando a aplicação sobe.

### 2. Definir as variáveis de ambiente

```bash
export DB_USERNAME=imdconecta_user
export DB_PASSWORD=senha_exemplo
```

### 3. Subir a aplicação

```bash
cd backend
./mvnw spring-boot:run
```

A API fica em `http://localhost:8080`.

### 4. Rodar os testes

```bash
cd backend
./mvnw test
```

### 5. Testar o cadastro (US1)

Cadastro válido (esperado: `201 Created`):

```bash
curl -i -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nome":"Aluno Teste","email":"aluno@ufrn.edu.br","senha":"senha123"}'
```

E-mail fora do domínio (esperado: `400`, com mensagem citando `@ufrn.edu.br`):

```bash
curl -i -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nome":"Aluno Teste","email":"aluno@gmail.com","senha":"senha123"}'
```

Repetir o primeiro comando devolve `409 Conflict` (e-mail já cadastrado).

## Integração contínua

O workflow em `.github/workflows/ci.yml` compila e roda os testes a cada push e pull request, usando um Postgres temporário.

## Vídeo da Sprint 1

_[link — a preencher]_