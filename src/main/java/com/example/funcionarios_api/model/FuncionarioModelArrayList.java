package com.example.funcionarios_api.model;

import com.example.funcionarios_api.entity.Funcionario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioModelArrayList implements FuncionarioModel {

    private final List<Funcionario> funcionarios = new ArrayList<>();
    private Long proximoId = 1L;

    @Override
    public List<Funcionario> listar() {
        return funcionarios;
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarios.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    @Override
    public Funcionario criar(Funcionario funcionario) {
        Funcionario novo = new Funcionario(
                proximoId++,
                funcionario.getNome(),
                funcionario.getSalario(),
                funcionario.getDepartamentoId()
        );

        funcionarios.add(novo);
        return novo;
    }

    @Override
    public Optional<Funcionario> atualizar(Long id, Funcionario dados) {
        return buscarPorId(id).map(funcionario -> {
            funcionario.setNome(dados.getNome());
            funcionario.setSalario(dados.getSalario());
            funcionario.setDepartamentoId(dados.getDepartamentoId());
            return funcionario;
        });
    }

    @Override
    public boolean excluir(Long id) {
        return funcionarios.removeIf(f -> f.getId().equals(id));
    }

    @Override
    public List<Funcionario> listarPorDepartamento(Long departamentoId) {
        return funcionarios.stream()
                .filter(f -> f.getDepartamentoId().equals(departamentoId))
                .toList();
    }
}