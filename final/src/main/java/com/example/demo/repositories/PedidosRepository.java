package com.example.demo.repositories;

import com.example.demo.models.ClienteModel;
import com.example.demo.models.PedidosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidosRepository extends JpaRepository<PedidosModel, UUID> {
    List<PedidosModel> findByStatus(String status);

    boolean existsByCliente(ClienteModel cliente);
}
