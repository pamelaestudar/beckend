package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class ClienteDTO {

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private String telefone;

    private String endereco; // O endereço é opcional

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
