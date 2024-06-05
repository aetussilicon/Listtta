# Backend da plataforma LISTTTA

## LISTTTA é uma plataforma para que seus usuários encontrem tatuadores ou body piercers em sua região.

- [Descrição](#description)
- [Índice](#index)
- [Dependências](#dependences)
- [Variáveis de ambiente](#enviroment)
- [Estrutura do código](#code-structure)
- [Endpoints](#endpoints)

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

## Estrutura do Projeto

Este projeto segue a arquitetura em camadas, proporcionando uma separação clara de responsabilidades e facilitando a manutenção, escalabilidade e testabilidade do código.

### Arquitetura em Camadas

#### 1. **Camada de Apresentação (Presentation Layer)**

**Descrição**: Esta camada é responsável por lidar com a interação do usuário, recebendo as requisições HTTP e retornando as respostas adequadas.

**Pacote(s) envolvido(s)**: 
- `controller`

**Exemplo**:
- `AuthController`: Gerencia a autenticação dos usuários.
- `UsersController`: Lida com operações relacionadas aos usuários.

#### 2. **Camada de Serviço (Service Layer)**

**Descrição**: Esta camada contém a lógica de negócios da aplicação. Ela processa os dados entre a camada de apresentação e a camada de acesso a dados.

**Pacote(s) envolvido(s)**:
- `service`

**Exemplo**:
- `UsersService`: Contém a lógica de negócios relacionada a usuários.
- `ProfessionalsService`: Contém a lógica de negócios relacionada a profissionais.

#### 3. **Camada de Acesso a Dados (Data Access Layer)**

**Descrição**: Esta camada é responsável por interagir com o banco de dados, abstraindo a complexidade do acesso aos dados e proporcionando uma interface simplificada para a camada de serviço.

**Pacote(s) envolvido(s)**:
- `repository`

**Exemplo**:
- `UsersRepository`: Interface para operações de banco de dados relacionadas aos usuários.
- `ProfessionalsRepository`: Interface para operações de banco de dados relacionadas aos profissionais.

#### 4. **Camada de Modelo (Model Layer)**

**Descrição**: Esta camada contém as entidades JPA, DTOs (Data Transfer Objects), enums e mappers. As entidades representam as tabelas do banco de dados, os DTOs são usados para transferência de dados entre as camadas, e os mappers transformam dados entre diferentes tipos de objetos.

**Pacote(s) envolvido(s)**:
- `model`

**Subpacotes**:
- `dto`: Objetos de transferência de dados.
- `entities`: Entidades JPA.
- `enums`: Enumerações.
- `mapper`: Classes de mapeamento.

#### 5. **Camada de Exceções (Exception Layer)**

**Descrição**: Contém exceções personalizadas que são lançadas para lidar com situações específicas de erro. Ajuda a fornecer mensagens de erro claras e específicas para os usuários.

**Pacote(s) envolvido(s)**:
- `exceptions`

**Exemplo**:
- `UserNotFoundException`: Lançada quando um usuário não é encontrado.
- `ProfessionalDetailsNotFoundException`: Lançada quando os detalhes de um profissional não são encontrados.

#### 6. **Utilitários (Utility Layer)**

**Descrição**: Contém classes utilitárias e auxiliares que fornecem funcionalidades gerais, como geração de UUIDs ou configurações de inicialização.

**Pacote(s) envolvido(s)**:
- `util`

**Exemplo**:
- `PuidGenerator`: Classe para gerar PUIDs (Public User IDentification).
- `BackendApplication`: Classe principal que inicia a aplicação Spring Boot.

#### 7. **Recursos (Resources)**

**Descrição**: Contém arquivos de configuração e scripts de migração do banco de dados.

**Diretórios**:
- `resources`
  - `db.migration`: Scripts de migração do banco de dados.
  - `application.properties`: Arquivo de configuração do Spring Boot.

### Diagrama de Camadas

```plaintext
Presentation Layer
    │
    └── controller
        ├── AuthController.java
        ├── FiltersController.java
        ├── ProfessionalsController.java
        └── UsersController.java
    │
Service Layer
    │
    └── service
        ├── AddressService.java
        ├── AuthorizationService.java
        ├── FiltersService.java
        ├── ProfessionalsService.java
        ├── ProfessionalsSkillsService.java
        ├── UsersService.java
        └── UsersViewService.java
    │
Data Access Layer
    │
    └── repository
        ├── AddressRepository.java
        ├── FiltersRepository.java
        ├── ProfessionalsRepository.java
        ├── ProfessionalsSkillsRepository.java
        ├── ProfessionalViewRepository.java
        ├── UsersRepository.java
        └── UsersViewRepository.java
    │
Model Layer
    │
    └── model
        ├── dto
        ├── entities
        ├── enums
        └── mapper
    │
Exception Layer
    │
    └── exceptions
        ├── ProfessionalDetailsNotFoundException.java
        ├── UpdateFieldIsException.java
        ├── UserAlreadyInDatabaseException.java
        └── UserNotFoundException.java
    │
Utility Layer
    │
    └── util
        ├── BackendApplication.java
        └── PuidGenerator.java
    │
Resources
    │
    └── resources
        ├── db.migration
        └── application.properties
```
## Endpoints

### O LISTTTA possui os seguintes endpoints:

### Autenticação
- **Cadastro**: `/auth/signup`
- **Login**: `/auth/login`

### Usuários

- **Listar usuário único**: `/users/list/{puid}`
- **Listar todos os usuários**: `/users/list/all`
- **Update do usuário**: `/users/update/{puid}`

### Profissionais 

- **Listar profissional único**: `/users/list/{puid}`
- **Listar todos os profissional**: `/professionals/list/all`
- **Update do usuários**: `/professionals/update/{puid}`

### Filtros/Especialidades 

- **Criar novo filtro**: `/filters/create`
- **Listar filtro único**: `/filters/list/{filterName}`
- **Listar todos os filtro**: `/filters/list/all`
- **Update do usuário**: `/filters/update/{filterId}`
- **Deletar filtro**: `/filters/delete/{filterId}`
