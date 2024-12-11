package com.example.demo.controllers;

import com.example.demo.dtos.ClienteDTO;
import com.example.demo.models.ClienteModel;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")  // Prefixo da URL para todas as rotas desse controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteModel> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteModel cliente = clienteService.salvarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> obterCliente(@PathVariable UUID id) {
        Optional<ClienteModel> cliente = clienteService.obterCliente(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> editarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteModel cliente = clienteService.editarCliente(id, clienteDTO);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable UUID id) {
        boolean excluido = clienteService.excluirCliente(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
