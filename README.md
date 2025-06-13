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

* **controller**

  * `console.Console` (entrada de dados)
  * `FuncionarioController` (CRUD de funcionários, persistência em arquivo)
  * `FilmeController` (CRUD de filmes, validação de duplicatas)
  * `ClienteController` (CRUD de clientes, validação de CPF)
  * `LocacaoController` (registrar locações e gerar relatórios)

* **view**

  * `Principal` (menu principal, delega operações aos controllers)

Relações principais:

* **Herança:** `Funcionario` é superclasse de `Gerente` e `Atendente`
* **Interface:** `Trabalhavel` implementada por `Gerente` e `Atendente`
* **Associação:** `Locacao` associa `Cliente` e `Filme`
* **Agregação:** cada controller mantém uma coleção (`List<>`) de sua respectiva entidade

---

**Autores:** Matheus Miguel Barbosa
**Data de Entrega:** 16/06/2025
