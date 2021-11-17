# desafio-iti

Repositório para resolver o desafio https://github.com/itidigital/backend-challenge

### Descrição

Este projeto é para atender o desafio de validação de senha proposto pelo time ITI e a definição de senha válida é:

    Nove ou mais caracteres
    Ao menos 1 dígito
    Ao menos 1 letra minúscula
    Ao menos 1 letra maiúscula
    Ao menos 1 caractere especial
        Considere como especial os seguintes caracteres: !@#$%^&*()-+
    Não possuir caracteres repetidos dentro do conjunto

Exemplo:

```
IsValid("") // false  
IsValid("aa") // false  
IsValid("ab") // false  
IsValid("AAAbbbCc") // false  
IsValid("AbTp9!foo") // false  
IsValid("AbTp9!foA") // false
IsValid("AbTp9 fok") // false
IsValid("AbTp9!fok") // true
```

> Nota: Espaços em branco não devem ser considerados como caracteres válidos.

### Incluindo novas regras

A inclusão de novas regras é algo bem simples:

1. Criar uma classe implementando a interface `br.com.desafio.iti.regra.Regra`;
2. Adicionar a nova regra no método `br.com.desafio.iti.DesafioItiApplication#regrasValidacao()`.

Seguindo esses passos, a nova regra de validação será incluída pra ser executada e não é necessário nenhuma outra
alteração no projeto.

### Requisitos mínimos

- Java 8
- Maven 3.6

### Como executar o projeto

Após instalar os requisitos mínimos, é possível rodar o projeto usando comando:

> mvn spring-boot:run

A aplicação será iniciada e estará disponível na porta 8080.

### Como executar os testes

A execução dos testes pode ser feita através do comando:

> mvn test

### APIs disponíveis

#### Health Check

Indica a saúde da aplicação.

```
GET /actuator/health
```

##### Resposta

```
{
    "status": "UP"
}
```

| Atributo | Descrição   |
|---|---|
| status  | Estado de saúde da aplicação  |

###### Códigos de Retorno

| Código | Descrição   |
|---|---|
| 200  | Requisição com sucesso  |
| 500  | Erro interno  |

#### Validação de senha

Verifica se uma senha atende aos requisitos mínimos de segurança.

```
POST /iti/validacao/senha
```

##### Requisição

```
{
    "senha": "Senha123@"
}
```

| Atributo | Descrição   |
|---|---|
| senha  | Senha a ser validada. Atributo obrigatório  |

##### Resposta

```
{
    "senhaValida":true
}
```

| Atributo | Descrição   |
|---|---|
| senhaValida  | Booleano indicando se a senha é válida ou não.  |
| mensagemErro | Caso a senha seja inválida retorna a descrição do erro.  |


###### Códigos de Retorno

| Código | Descrição   |
|---|---|
| 200  | Requisição com sucesso  |
| 400  | O corpo da requisição está errado  |
| 500  | Erro interno  |


