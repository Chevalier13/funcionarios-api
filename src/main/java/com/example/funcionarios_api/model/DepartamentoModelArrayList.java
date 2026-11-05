package com.example.funcionarios_api.model;

import com.example.funcionarios_api.entity.Departamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoModelArrayList implements DepartamentoModel {

    private final List<Departamento> departamentos = new ArrayList<>();

    public DepartamentoModelArrayList() {
        departamentos.add(new Departamento(1L, "Tecnologia"));
        departamentos.add(new Departamento(2L, "Recursos Humanos"));
        departamentos.add(new Departamento(3L, "Financeiro"));
    }

    @Override
    public List<Departamento> listar() {
        return departamentos;
    }

    @Override
    public Optional<Departamento> buscarPorId(Long id) {
        return departamentos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }
}