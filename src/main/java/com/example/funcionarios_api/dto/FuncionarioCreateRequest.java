package com.example.funcionarios_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class FuncionarioCreateRequest {

    @NotBlank(message = "não pode ser vazio")
    @Size(min = 3, message = "deve ter no mínimo 3 caracteres")
    private String nome;

    @NotNull(message = "não pode ser nulo")
    @DecimalMin(value = "1412.00", message = "deve ser maior ou igual a 1412.00")
    private BigDecimal salario;

    @NotNull(message = "não pode ser nulo")
    private Long departamentoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }
}