package com.ufpr.backcontrolapedido.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufpr.backcontrolapedido.model.dto.PedidoDTO;
import com.ufpr.backcontrolapedido.model.entities.Pedido;
import com.ufpr.backcontrolapedido.repository.PedidoRepository;
import com.ufpr.backcontrolapedido.service.exception.ResourceNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // recupera todos os pedidos
    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());
    }

    // recupera pedido por id específico
    public PedidoDTO findById(Long id) {
        Pedido entity = getEntityById(id);
        return new PedidoDTO(entity);
    }

    // insere pedido no banco de dados
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido entity = new Pedido();
        copyDtoToEntity(dto, entity);
        entity = pedidoRepository.save(entity);
        return new PedidoDTO(entity);
    }

    // deleta pedido por id
    public void delete(Long id) {
        getEntityById(id);
        pedidoRepository.deleteById(id);
    }

    // método encapsulado para recuperar entidade pedido por id do banco de dados
    private Pedido getEntityById(Long id) {
        Optional<Pedido> result = pedidoRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado."));
    }

    // método encapsulado que transfere objeto DTO (Data transfer object) para a
    // entidade que terá contato com banco de dados diretamente
    private void copyDtoToEntity(PedidoDTO dto, Pedido entity) {
        entity.setCliente(dto.getCliente());
        entity.setData(dto.getData());
        entity.setItens(dto.getItens());
    }
}
