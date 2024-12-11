package com.example.demo.services;

import com.example.demo.dtos.ClienteDTO;
import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Salvar Cliente (valida duplicidade de CPF)
    public ClienteModel salvarCliente(ClienteDTO clienteDTO) {
        // Verifica se já existe um cliente com o mesmo CPF
        if (clienteRepository.findByCpf(clienteDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        ClienteModel cliente = new ClienteModel();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        return clienteRepository.save(cliente);
    }

    // Listar todos os clientes
    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll();
    }

    // Obter cliente por ID
    public Optional<ClienteModel> obterCliente(UUID id) {
        return clienteRepository.findById(id);
    }

    // Editar Cliente (valida duplicidade de CPF)
    public ClienteModel editarCliente(UUID id, ClienteDTO clienteDTO) {
        // Verifica se o cliente existe
        ClienteModel clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Verifica se o CPF já está sendo usado por outro cliente
        Optional<ClienteModel> clienteComMesmoCpf = clienteRepository.findByCpf(clienteDTO.getCpf());
        if (clienteComMesmoCpf.isPresent() && !clienteComMesmoCpf.get().getId().equals(id)) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setCpf(clienteDTO.getCpf());
        clienteExistente.setTelefone(clienteDTO.getTelefone());
        clienteExistente.setEndereco(clienteDTO.getEndereco());

        return clienteRepository.save(clienteExistente);
    }

    @Autowired
    private PedidosRepository pedidosRepository;

    public boolean excluirCliente(UUID id) {
        // Verifica se o cliente existe
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Verifica se o cliente possui pedidos associados
        boolean possuiPedidos = pedidosRepository.existsByCliente(cliente);

        if (possuiPedidos) {
            throw new IllegalStateException("Não é possível excluir o cliente. O cliente possui pedidos associados.");
        }

        clienteRepository.deleteById(id);
        return true;
    }


}
