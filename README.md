# Event-City
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/RodriguesLucas/bds02/blob/add-license-1/LICENSE)

Event-City é uma aplicação Backend, desenvolvida para avaliação no decorrer de um Bootcamp Spring, curso realizado pela [DevSuperior](https://learn.devsuperior.com/).

A aplicação consiste em um TDD(Test-Driven Development), onde foi necessário implementar alguns end points como criar, visualizar e excluir para a City e um end point para atualizar o Event. Essas implementações foram necessárias para passar nos testes feitos antes da implentação do programa.

![Modelo Conceitual](https://github.com/RodriguesLucas/bds02/blob/main/ASSETS/ModeloConceitual.png)
# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- Jpa / Hibernate
- Maven
- JUnit Test

# Como executar o projeto
## Back end
Pré-requisito: Java 11

```bash
# Clonar o repositório
git clone https://github.com/RodriguesLucas/bds02

# Entrar na pasta do projeto back end
cd backend

# Executar o projeto
./mvnw spring-boot:run

```
```bash
# Sugestão: Usar o Postman para testar as requisições
  # City
    URL padrão da requisição: http://localhost:8080/cities

    Get: http://localhost:8080/cities

    Post: http://localhost:8080/cities

    Put: http://localhost:8080/cities/{id}

    Delete: http://localhost:8080/cities/{id}
   # Event
     Put: http://localhost:8080/events/{id}
```
# Autor
Lucas Rodrigues

https://www.linkedin.com/in/lucas-rodrigues-0558a3205/
