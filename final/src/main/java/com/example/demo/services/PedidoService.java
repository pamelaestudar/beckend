package com.example.demo.services;

import com.example.demo.dtos.PedidoDTO;
import com.example.demo.models.PedidosModel;
import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.PedidosRepository;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public PedidosModel salvarPedido(PedidoDTO pedidoDTO) {
        // Verificar se o cliente existe
        ClienteModel cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Converter DTO para a entidade
        PedidosModel pedido = new PedidosModel();
        pedido.setDescricao(pedidoDTO.getDescricao());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setCliente(cliente);

        // Salvar o pedido no banco
        return pedidosRepository.save(pedido);
    }

    // Método para listar todos os pedidos
    public List<PedidosModel> listarPedidos() {
        return pedidosRepository.findAll();
    }

    // Método para obter um pedido específico pelo ID
    public Optional<PedidosModel> obterPedido(UUID id) {
        return pedidosRepository.findById(id);
    }

    // Método para editar um pedido existente
    @Transactional
    public PedidosModel editarPedido(UUID id, PedidoDTO pedidoDTO) {
        PedidosModel pedido = pedidosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        ClienteModel cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        pedido.setDescricao(pedidoDTO.getDescricao());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setCliente(cliente);

        return pedidosRepository.save(pedido);
    }

    // Método para excluir um pedido pelo ID
    public boolean excluirPedido(UUID id) {
        if (pedidosRepository.existsById(id)) {
            pedidosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
