package com.ufpr.backcontrolapedido.model.dto;

import java.util.List;

import com.ufpr.backcontrolapedido.model.entities.Cliente;
import com.ufpr.backcontrolapedido.model.entities.ItemPedido;
import com.ufpr.backcontrolapedido.model.entities.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private String data;
    private Cliente cliente;
    private List<ItemPedido> itens;

    public PedidoDTO(Pedido entity) {
        this.id = entity.getId();
        this.data = entity.getData();
        this.cliente = entity.getCliente();
        this.itens = entity.getItens();
    }

}
