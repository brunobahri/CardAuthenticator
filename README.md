# CardAuthenticator

## Resumo da API

A API CardAuthenticator é projetada para oferecer uma solução robusta e segura para a validação de dados de cartões de crédito. Ela permite a identificação da bandeira do cartão e a validação do número do cartão utilizando o algoritmo de Luhn, além de verificar a data de validade do cartão. Essa API é ideal para sistemas que necessitam de uma verificação rápida e confiável da autenticidade de informações de cartões de crédito, como plataformas de e-commerce, sistemas bancários e financeiros.

## Instalação

### Pré-requisitos

Java JDK 11 ou superior.
Maven ou Gradle para gerenciamento de dependências e automação de build.
Spring Boot para facilitar a configuração e o lançamento da aplicação.

### Passos para Instalação

### Clone o repositório:
```
git clone https://github.com/brunobahri/CardAuthenticator
cd CardAuthenticator
```
### Construa o projeto:
Com Maven:
```
mvn clean install
```
Com Gradle:
```
./gradlew build
```
### Execute a aplicação:
Com Maven:
```
mvn spring-boot:run
```
Com Gradle:
```
./gradlew bootRun
```
## Utilização da API

### Endpoints Principais

#### POST /validateCard: Endpoint para validar os dados do cartão de crédito.

#### Body:
```
{
  "cardNumber": "4532015112830366",
  "expiryDate": "12/24",
  "cvv": "123",
  "cardHolderFirstName": "John",
  "cardHolderLastName": "Doe"
}
```
#### Respostas:
#### Sucesso:
```
{
  "message": "Cartão válido",
  "brand": "Visa"
}
```
#### Erro:
```
{
  "error": "Número do cartão inválido"
}
```
### Adicionais: 

Swagger UI: Para uma documentação interativa, configure o Swagger para visualizar e interagir com a API diretamente através de um navegador.
Postman: Utilize a coleção do Postman para testar os endpoints de forma prática.
