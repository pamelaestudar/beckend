package com.example.demo.controllers;

import com.example.demo.dtos.PedidoDTO;
import com.example.demo.models.PedidosModel;
import com.example.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")  // Prefixo da URL para todas as rotas desse controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Criar pedido
    @PostMapping
    public ResponseEntity<PedidosModel> criarPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
        PedidosModel pedido = pedidoService.salvarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    // Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<PedidosModel>> listarPedidos() {
        List<PedidosModel> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // Obter pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidosModel> obterPedido(@PathVariable UUID id) {
        Optional<PedidosModel> pedido = pedidoService.obterPedido(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Editar pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidosModel> editarPedido(@PathVariable UUID id, @RequestBody @Valid PedidoDTO pedidoDTO) {
        PedidosModel pedido = pedidoService.editarPedido(id, pedidoDTO);
        return ResponseEntity.ok(pedido);
    }

    // Excluir pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable UUID id) {
        boolean excluido = pedidoService.excluirPedido(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
