# 🚀 **CRUD de Produtos** 🚀

Bem-vindo ao repositório do projeto **CRUD de Produtos**! Este projeto foi desenvolvido para demonstrar minhas habilidades como **Desenvolvedor Backend** e **Estudante de Engenharia de Software**. A aplicação foi construída com **Spring Boot**, utilizando boas práticas de desenvolvimento, princípios **SOLID** e **Clean Code**.

### 🔧 **Status do Projeto: Concluído** 🔧
O projeto está finalizado e funcional, com todas as operações **CRUD** implementadas e testadas. Novas **features** e melhorias podem ser adicionadas no futuro.

### 🚀 **Recursos do Projeto:**
- **Criar Produto**: Adiciona um novo produto ao sistema.
- **Listar Produtos**: Retorna todos os produtos cadastrados.
- **Buscar Produto por ID**: Retorna um produto específico com base no ID.
- **Atualizar Produto**: Atualiza as informações de um produto existente.
- **Excluir Produto**: Remove um produto do sistema.

### 🛠️ **Tecnologias Utilizadas:**
- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para acesso e gerenciamento de dados.
- **MySQL**: Banco de dados principal para ambiente de produção/desenvolvimento.
- **H2 Database**: Banco de dados em memória para testes.
- **HATEOAS**: Para adicionar hypermedia (links) às respostas da API.
- **Swagger/OpenAPI**: Para documentação da API.
- **JUnit 5 e Mockito**: Para testes unitários e de integração.
- **Maven**: Para gerenciamento de dependências e build do projeto.

### 🌐 **Acesse a Documentação da API:**
A API está documentada com **Swagger**. Para acessar a documentação, execute o projeto e acesse:

[Swagger UI](http://localhost:8080/swagger-ui.html)

### 🧪 **Testes:**
O projeto inclui testes unitários e de integração para garantir a qualidade do código:

- **Testes Unitários**: Testes para **ProdutoService** e **ProdutoController** usando **JUnit 5** e **Mockito**.
- **Testes de Integração**: Testes que verificam a integração entre o controlador, serviço e banco de dados **H2**.
- **Cenários de Sucesso e Erro**: Testes cobrem cenários de sucesso (ex: produto encontrado) e erro (ex: produto não encontrado).

### 🧠 **Princípios SOLID Aplicados:**

- **Single Responsibility Principle (SRP)**: Cada classe tem uma única responsabilidade. Por exemplo:
  - **ProdutoService**: Lida com a lógica de negócio.
  - **ProdutoRepository**: Gerencia o acesso aos dados.
  - **ProdutoController**: Gerencia as requisições HTTP.

- **Open/Closed Principle (OCP)**: O código está aberto para extensão, mas fechado para modificação. Novos métodos podem ser adicionados sem alterar o comportamento existente.

- **Liskov Substitution Principle (LSP)**: As classes filhas (como **ProdutoService**) podem substituir suas classes base sem alterar o comportamento do sistema.

- **Interface Segregation Principle (ISP)**: Interfaces específicas são usadas para cada funcionalidade, como **ProdutoRepository** para operações de banco de dados.

- **Dependency Inversion Principle (DIP)**: Dependências são injetadas via **Spring** (ex: **@Autowired**), permitindo baixo acoplamento e facilidade de testes.

### ⚙️ **Boas Práticas de Clean Code:**
- **Nomes Significativos**: Classes, métodos e variáveis têm nomes descritivos, como **ProdutoService**, **listarTodos**, **buscarPorId**.
- **Funções Pequenas e Específicas**: Cada método tem uma única responsabilidade e é curto, facilitando a leitura e o entendimento.
- **Comentários Úteis**: Comentários são usados apenas quando necessário, para explicar decisões complexas ou regras de negócio.
- **Tratamento de Erros**: Respostas HTTP adequadas são retornadas em caso de erros, como 404 **Not Found** para produtos inexistentes.
- **Testes Automatizados**: Testes unitários e de integração foram implementados para garantir a qualidade do código.

### 🔗 **HATEOAS e Documentação da API:**

- **HATEOAS**: A API inclui links HATEOAS nas respostas, permitindo que os clientes naveguem entre os recursos de forma dinâmica. Por exemplo:
  - **self**: Link para o próprio recurso.
  - **atualizar**: Link para atualizar o produto.
  - **deletar**: Link para excluir o produto.

- **Swagger/OpenAPI**: A API está documentada com **Swagger**, permitindo que os desenvolvedores visualizem e testem os endpoints diretamente no navegador.

---

Esse é o **README.md** do seu projeto, que agora inclui os princípios **SOLID**, boas práticas de **Clean Code** e **HATEOAS** aplicados! Está pronto para ser compartilhado com outros desenvolvedores ou utilizado para sua documentação.
