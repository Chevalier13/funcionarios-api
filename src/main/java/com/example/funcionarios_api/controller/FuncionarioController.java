
package com.example.funcionarios_api.controller;

import com.example.funcionarios_api.dto.FuncionarioCreateRequest;
import com.example.funcionarios_api.dto.FuncionarioResponse;
import com.example.funcionarios_api.entity.Funcionario;
import com.example.funcionarios_api.exception.InvalidFieldException;
import com.example.funcionarios_api.exception.ResourceNotFoundException;
import com.example.funcionarios_api.mapper.FuncionarioMapper;
import com.example.funcionarios_api.model.DepartamentoModel;
import com.example.funcionarios_api.model.FuncionarioModel;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionario", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuncionarioController {

    private final FuncionarioModel funcionarioModel;
    private final DepartamentoModel departamentoModel;

    public FuncionarioController(
            FuncionarioModel funcionarioModel,
            DepartamentoModel departamentoModel) {
        this.funcionarioModel = funcionarioModel;
        this.departamentoModel = departamentoModel;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal salarioMinimo) {

        List<FuncionarioResponse> resultado = funcionarioModel.listar()
                .stream()
                .filter(f -> nome == null ||
                        f.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(f -> salarioMinimo == null ||
                        f.getSalario().compareTo(salarioMinimo) >= 0)
                .map(FuncionarioMapper::toResponse)
                .toList();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscarPorId(@PathVariable Long id) {

        Funcionario funcionario = funcionarioModel.buscarPorId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Funcionário não encontrado"));

        return ResponseEntity.ok(FuncionarioMapper.toResponse(funcionario));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioResponse> criar(
            @Valid @RequestBody FuncionarioCreateRequest request) {

        validarDepartamento(request.getDepartamentoId());

        Funcionario funcionario = FuncionarioMapper.toEntity(request);
        Funcionario novo = funcionarioModel.criar(funcionario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/funcionario/{id}")
                .buildAndExpand(novo.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(FuncionarioMapper.toResponse(novo));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody FuncionarioCreateRequest request) {

        funcionarioModel.buscarPorId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Funcionário não encontrado"));

        validarDepartamento(request.getDepartamentoId());

        Funcionario dados = FuncionarioMapper.toEntity(request);

        Funcionario atualizado = funcionarioModel.atualizar(id, dados)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Funcionário não encontrado"));

        return ResponseEntity.ok(FuncionarioMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        boolean excluido = funcionarioModel.excluir(id);

        if (!excluido) {
            throw new ResourceNotFoundException("Funcionário não encontrado");
        }

        return ResponseEntity.noContent().build();
    }

    private void validarDepartamento(Long departamentoId) {
        if (departamentoModel.buscarPorId(departamentoId).isEmpty()) {
            throw new InvalidFieldException(
                    "departamentoId",
                    "departamento inexistente"
            );
        }
    }
}
