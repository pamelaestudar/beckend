package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class PedidoDTO {

    @NotNull
    private String descricao;

    @NotNull
    private Double valorTotal;

    @NotNull
    private String status;

    @NotNull
    private UUID clienteId; // ID do cliente para vincular ao pedido

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }
}
