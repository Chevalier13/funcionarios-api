package com.example.funcionarios_api.model;

import com.example.funcionarios_api.entity.Departamento;
import java.util.List;
import java.util.Optional;

public interface DepartamentoModel {

    List<Departamento> listar();

    Optional<Departamento> buscarPorId(Long id);
}