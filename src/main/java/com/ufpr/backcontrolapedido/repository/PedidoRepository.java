package com.ufpr.backcontrolapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.backcontrolapedido.model.entities.Cliente;
import com.ufpr.backcontrolapedido.model.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsPedidoByCliente(Cliente cliente);
}
