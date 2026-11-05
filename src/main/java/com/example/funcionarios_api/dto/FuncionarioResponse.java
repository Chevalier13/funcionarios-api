package com.example.funcionarios_api.dto;

import java.math.BigDecimal;

public class FuncionarioResponse {

    private Long id;
    private String nome;
    private BigDecimal salario;
    private Long departamentoId;

    public FuncionarioResponse(Long id, String nome, BigDecimal salario, Long departamentoId) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.departamentoId = departamentoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }
}