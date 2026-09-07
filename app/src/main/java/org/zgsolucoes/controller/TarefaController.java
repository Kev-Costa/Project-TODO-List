package org.zgsolucoes.controller;

import org.zgsolucoes.model.Status;
import org.zgsolucoes.model.Tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TarefaController {
    private List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        Collections.sort(tarefas);
    }

    public boolean removerTarefaPorNome(String nome) {
        return tarefas.removeIf(tarefa -> tarefa.getNome().equalsIgnoreCase(nome));
    }

    public boolean alterarStatusTarefa(String nome, Status novoStatus) {
        for (Tarefa t : tarefas) {
            if (t.getNome().equalsIgnoreCase(nome)) {
                t.setStatus(novoStatus);
                return true; //Alterado com sucesso
            }
        }
        return false; //Tarefa nao encontrada
    }

    //Retorna lista completa ja ordenada
    public List<Tarefa> listarTodas() {
        return tarefas;
    }

    //Filtrar por Status
    public List<Tarefa> listarPorStatus(Status status) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .toList();
    }

    //Filtrar por prioridade
    public List<Tarefa> listarPorPrioridade(int prioridade) {
        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .toList();
    }

    //Filtar por categoria
    public List<Tarefa> listarPorCategoria(String categoria) {
        return tarefas.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

}
