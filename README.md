# Backend da plataforma LISTTTA

## LISTTTA é uma plataforma para que seus usuários encontrem tatuadores ou body piercers em sua região.

- [Descrição](#description)
- [Índice](#index)
- [Dependências](#dependences)
- [Variáveis de ambiente](#enviroment)
- [Estrutura do código](#code-structure)
- [Uso](#use)
- [API](#api)

## Dependências
### O LISTTTA é uma plataforma construida em *Java 17* e *Spring*.

- Java 17
- PostgreSQL 
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Validation
- Spring Security
- Spring Web
- Flyway Migrations
- JWT 4.4.0
- Lombok
- Caelum Stella Core (Validação de CPFs e CNPJs)
- MapStruct 1.5.5.Final
- JUnit
- Mockito

## Variáveis de Ambiente

### O LISTTTA usa algumas variáveis de ambientes para que possa iniciar de qualquer lugar que possua essas variáveis.

1. **POSTGRES_URL**: URL para o banco de dados. 
    - Algo como: `jdbc:postgresql://localhost:5432/database`

2. **POSTGRES_USER**: Usuário do banco de dados.
3. **POSTGRES_PASSWD**: Senha do usuário.
4. **JWT_SECRET**: Chave encriptada para o token. Deve ser criada ou utilizar a chave pública. Localizada em .ssh do servidor.

