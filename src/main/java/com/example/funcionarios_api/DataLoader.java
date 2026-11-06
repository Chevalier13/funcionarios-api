package com.example.funcionarios_api;

import com.example.funcionarios_api.entity.Funcionario;
import com.example.funcionarios_api.model.FuncionarioModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final FuncionarioModel funcionarioModel;

    public DataLoader(FuncionarioModel funcionarioModel) {
        this.funcionarioModel = funcionarioModel;
    }

    @Override
    public void run(String... args) {
        funcionarioModel.criar(new Funcionario(
                null,
                "Alice Silva",
                new BigDecimal("7500.00"),
                1L
        ));

        funcionarioModel.criar(new Funcionario(
                null,
                "Carlos Souza",
                new BigDecimal("4200.00"),
                2L
        ));

        funcionarioModel.criar(new Funcionario(
                null,
                "Marcos Paulo",
                new BigDecimal("6100.00"),
                3L
        ));
    }
}