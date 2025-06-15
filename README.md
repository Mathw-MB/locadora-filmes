# Locadora de Filmes

**Descrição Geral**

Este projeto simula o sistema de gerenciamento de uma locadora de filmes, permitindo:

* Cadastro e listagem de funcionários (Gerentes e Atendentes)
* Cadastro e busca de filmes por título e gênero
* Cadastro e listagem de clientes com validação de CPF
* Registro de locações com verificação de datas
* Geração de relatórios de locações por filme e por gênero

O objetivo é aplicar conceitos de **Orientação a Objetos**, **Herança**, **Polimorfismo**, **Tratamento de Exceções**, **Leitura/Gravação de Arquivos** e **Clean Code**.

---

## Estrutura de Classes e Relações

* **model**

  * `Funcionario` (classe abstrata)

    * `Gerente` (implements `Trabalhavel`)
    * `Atendente` (implements `Trabalhavel`)
  * `Cliente` (entidade independente)
  * `Filme`
  * `Genero`
  * `Locacao` (associação: contém referências a `Filme` e `Cliente`)
  * `Trabalhavel`

* **controller**

  * `console.Console` (entrada de dados)
  * `FuncionarioController` (CRUD de funcionários, persistência em arquivo)
  * `FilmeController` (CRUD de filmes, validação de duplicatas)
  * `ClienteController` (CRUD de clientes, validação de CPF)
  * `LocacaoController` (registrar locações e gerar relatórios)

* **view**

  * `Principal` (menu principal, delega operações aos controllers)

**Relações principais:**

* **Herança:** `Funcionario` é superclasse de `Gerente` e `Atendente`
* **Interface:** `Trabalhavel` implementada por `Gerente` e `Atendente`
* **Associação:** `Locacao` associa `Cliente` e `Filme`
* **Agregação:** cada controller mantém uma coleção (`List<>`) de sua respectiva entidade

---

## Como Executar o Projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/Mathw-MB/locadora-filmes.git
   cd locadora-filmes
   ```

2. **Compilar**

   **Pelo terminal (na raiz do projeto):**

   ```bash
   javac -d bin -sourcepath src src/view/Principal.java
   ```

   **Ou no VS Code:**

   1. Abra a pasta do projeto:
      * **File ▶ Open Folder...**, navegue até a pasta `locadora-filmes` e clique em **OK**.
   2. Na aba **JAVA PROJECTS**, clique em **+** ao lado de `src`.
   3. Selecione **Add Folder to Java Source Path**.

3. **Executar**

   **Pelo terminal:**

   ```bash
   java -cp bin view.Principal
   ```

   **No VS Code:**

   1. Abra `src/view/Principal.java`.
   2. Clique em **Run** (ou aceite o **Add to Source Path**, se aparecer).

---

## Uso do ChatGPT

Usei o ChatGPT para:

* **Validações e Exceções:** implementar CPF, duplicatas, datas, e melhorar mensagens de erro.
* **Refatoração:** modularizar controllers e métodos estáticos.
* **Documentação:** gerar e formatar este `README.md`.
* **Suporte Git/GitHub e VS Code:** tirar dúvidas sobre configuração de workspace, comandos Git e execução.

---

## Referências e Recursos

* Slides e exercícios das **Aulas 04, 06, 07, 23 e 24**
* **ChatGPT (OpenAI)** – suporte à modelagem, correção e documentação

---

**Autor:** Matheus Miguel Barbosa
**Data de Entrega:** 16/06/2025
