package com.ufpr.backcontrolapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.backcontrolapedido.model.entities.Cliente;
import com.ufpr.backcontrolapedido.model.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsPedidoByCliente(Cliente cliente);
}
