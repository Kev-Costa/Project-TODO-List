package org.zgsolucoes.view;

import org.zgsolucoes.controller.TarefaController;
import org.zgsolucoes.model.Status;
import org.zgsolucoes.model.Tarefa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private TarefaController controller = new TarefaController();
    private Scanner scanner = new Scanner(System.in);

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- TODO LIST ---");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Remover tarefa");
            System.out.println("3. Alterar tarefa");
            System.out.println("4. Listar todas");
            System.out.println("5. Listar por Categoria");
            System.out.println("6. Listar por Prioridade");
            System.out.println("7. Listar por Status");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opçao");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarTarefa();
                case 2 -> deletarTarefa();
                case 3 -> atualizarStatus();
                case 4 -> listarTodas();
                case 5 -> listarPorCategoria();
                case 6 -> listarPorPrioridade();
                case 7 -> listarPorStatus();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opçao invalida!");
            }
        }
    }

    private void cadastrarTarefa() {
        System.out.println("\n--- CADASTRO DE TAREFA ---");

        System.out.println("Nome da tarefa: ");
        String nome = scanner.nextLine();

        System.out.println("Descriçao: ");
        String descricao = scanner.nextLine();

        LocalDate dataTermino = null;
        while (dataTermino == null) {
            try {
                System.out.println("Data de termino (AAAA-MM-DD): ");
                String dataStr = scanner.nextLine();
                dataTermino = LocalDate.parse(dataStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inavalido! Tente no formato Ex: 2026-12-32");
            }
        }

        System.out.println("Prioridade (1 - Menor, 5 - Maior): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Categoria: ");
        String categoria = scanner.nextLine();

        Status status = Status.TODO;

        Tarefa novaTarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);

        controller.adicionarTarefa(novaTarefa);

        System.out.println(">> Tarefa cadastrada e rebalanceada com sucesso!");
    }

    public void deletarTarefa() {
        System.out.println("\n--- Remover Tarefa ---");

        System.out.println("Nome da tarefa: ");
        String nome = scanner.nextLine();

        boolean removido = controller.removerTarefaPorNome(nome);
        if (removido) {
            System.out.println(">> Tarefa removida com sucesso!");
        } else {
            System.out.println(">> Tarefa nao encontrada");
        }
    }

    public void atualizarStatus() {
        System.out.println("\n--- Atualizar Status---");

        System.out.println("Nome da tarefa: ");
        String nome = scanner.nextLine();

        try {
            System.out.println("Status (TODO, DOING, DONE): ");
            Status status = Status.valueOf(scanner.nextLine().trim().toUpperCase());
            controller.alterarStatusTarefa(nome, status);
            System.out.println(">> Tarefa alterada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Status inavalido! Opçoes permitidas: TODO, DOING, DONE");
            System.out.println(">> Tarefa nao encontrada");
        }
    }

    public void listarTodas() {
        System.out.println("\n--- Listar todas Tarefa ---\n");
        List<Tarefa> tarefas = controller.listarTodas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (Tarefa t : tarefas) {
                System.out.println(t);
            }
        }
    }

    public void listarPorCategoria() {
        System.out.println("\n--- Listar por Categoria ---\n");

        System.out.println("Categoria: ");
        String categoria = scanner.nextLine();

        List<Tarefa> tarefas = controller.listarPorCategoria(categoria);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (Tarefa t : tarefas) {
                System.out.println(t);
            }
        }
    }

    public void listarPorPrioridade() {
        System.out.println("\n--- Listar por Prioridade---\n");

        System.out.println("Prioridade: ");
        int prioridade = scanner.nextInt();
        scanner.nextLine();

        List<Tarefa> tarefas = controller.listarPorPrioridade(prioridade);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (Tarefa t : tarefas) {
                System.out.println(t);
            }
        }
    }

    public void listarPorStatus() {
        System.out.println("\n--- Listar por Status ---\n");

        try {
            System.out.println("Status (TODO, DOING, DONE): ");
            Status status = Status.valueOf(scanner.nextLine().trim().toUpperCase());
            List<Tarefa> tarefas = controller.listarPorStatus(status);
            if (tarefas.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada.");
            } else {
                for (Tarefa t : tarefas) {
                    System.out.println(t);
                }
            };
        } catch (IllegalArgumentException e) {
            System.out.println("Status inavalido! Opçoes permitidas: TODO, DOING, DONE");
        }
    }

}
