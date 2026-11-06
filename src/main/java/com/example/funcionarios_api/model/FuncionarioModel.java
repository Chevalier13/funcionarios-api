package com.example.funcionarios_api.model;

import com.example.funcionarios_api.entity.Funcionario;
import java.util.List;
import java.util.Optional;

public interface FuncionarioModel {

    List<Funcionario> listar();

    Optional<Funcionario> buscarPorId(Long id);

    Funcionario criar(Funcionario funcionario);

    Optional<Funcionario> atualizar(Long id, Funcionario funcionario);

    boolean excluir(Long id);

    List<Funcionario> listarPorDepartamento(Long departamentoId);
}