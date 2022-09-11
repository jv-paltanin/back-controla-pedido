package com.ufpr.backcontrolapedido.model.dto;

import com.ufpr.backcontrolapedido.model.entities.ItemPedido;
import com.ufpr.backcontrolapedido.model.entities.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private int quantidade;
    private Produto produto;

    public ItemPedidoDTO(ItemPedido entity) {
        this.quantidade = entity.getQuantidade();
        this.produto = entity.getProduto();
    }

}
