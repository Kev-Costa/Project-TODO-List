# 📝 TODO List CLI - Java Pure & Gradle

Uma aplicação de gerenciamento de tarefas em modo de linha de comando (CLI) desenvolvida em **Java puro (Vanilla)** e construída com **Gradle**, aplicando o padrão arquitetural **MVC (Model-View-Controller)**.

Este projeto foi desenvolvido com o objetivo de reforçar conceitos fundamentais do ecossistema Java, estruturas de dados, orientação a objetos e rebalanceamento automático de prioridades sem a dependência de frameworks.

---

## 🎯 Funcionalidades

- **CRUD de Tarefas:** Criação, listagem e remoção de tarefas em memória.
- **Gerenciamento de Status:** Alteração de status entre `TODO`, `DOING` e `DONE`.
- **Rebalanceamento por Prioridade:** Ordenação automática da lista ao inserir novas tarefas (prioridades de 1 a 5).
- **Filtros Avançados:** Listagem parametrizada por **Categoria**, **Prioridade** e **Status**.
- **Interface de Terminal:** Menu interativo e intuitivo construído no console via `Scanner`.

---

## 🛠️ Tecnologias e Conceitos Utilizados

- **Linguagem:** Java (JDK 17+)
- **Gerenciador de Dependências e Build:** Gradle
- **Padrão Arquitetural:** MVC (Model-View-Controller)
- **Estruturas de Dados:** Java Collections (`List`, `ArrayList`)
- **Manipulação Functional:** Java Streams e Expressões Lambda
- **Contrato de Ordenação:** Interface `Comparable` (`compareTo`)
- **Datas:** API `java.time.LocalDate`

---

## 📐 Estrutura do Projeto

```text
com.zgsolucoes/
├── controller/
│   └── TarefaController.java  # Regras de negócio e filtros
├── model/
│   ├── Status.java            # Enum de estados da tarefa
│   └── Tarefa.java            # Entidade de dados e lógica de ordenação
├── view/
│   └── MenuView.java          # Interface do terminal e captura de entradas
└── App.java                   # Ponto de entrada da aplicação
