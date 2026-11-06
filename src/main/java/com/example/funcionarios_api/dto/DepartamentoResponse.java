package com.example.funcionarios_api.dto;

public class DepartamentoResponse {

    private Long id;
    private String nome;

    public DepartamentoResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}