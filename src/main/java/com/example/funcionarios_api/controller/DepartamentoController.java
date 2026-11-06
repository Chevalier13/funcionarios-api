
package com.example.funcionarios_api.controller;

import com.example.funcionarios_api.dto.DepartamentoResponse;
import com.example.funcionarios_api.dto.FuncionarioResponse;
import com.example.funcionarios_api.mapper.FuncionarioMapper;
import com.example.funcionarios_api.model.DepartamentoModel;
import com.example.funcionarios_api.model.FuncionarioModel;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartamentoController {

    private final DepartamentoModel departamentoModel;
    private final FuncionarioModel funcionarioModel;

    public DepartamentoController(
            DepartamentoModel departamentoModel,
            FuncionarioModel funcionarioModel) {
        this.departamentoModel = departamentoModel;
        this.funcionarioModel = funcionarioModel;
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoResponse>> listar(
            @RequestParam(required = false) String nome) {

        List<DepartamentoResponse> resultado = departamentoModel.listar()
                .stream()
                .filter(d -> nome == null ||
                        d.getNome().toLowerCase().contains(nome.toLowerCase()))
                .map(d -> new DepartamentoResponse(d.getId(), d.getNome()))
                .toList();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}/funcionario")
    public ResponseEntity<List<FuncionarioResponse>> listarFuncionarios(
            @PathVariable Long id) {

        List<FuncionarioResponse> resultado = funcionarioModel
                .listarPorDepartamento(id)
                .stream()
                .map(FuncionarioMapper::toResponse)
                .toList();

        return ResponseEntity.ok(resultado);
    }
}
