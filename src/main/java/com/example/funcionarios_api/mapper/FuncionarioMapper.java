package com.example.funcionarios_api.mapper;

import com.example.funcionarios_api.dto.FuncionarioCreateRequest;
import com.example.funcionarios_api.dto.FuncionarioResponse;
import com.example.funcionarios_api.entity.Funcionario;

public class FuncionarioMapper {

    public static Funcionario toEntity(FuncionarioCreateRequest request) {
        return new Funcionario(
                null,
                request.getNome(),
                request.getSalario(),
                request.getDepartamentoId()
        );
    }

    public static FuncionarioResponse toResponse(Funcionario funcionario) {
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSalario(),
                funcionario.getDepartamentoId()
        );
    }
}