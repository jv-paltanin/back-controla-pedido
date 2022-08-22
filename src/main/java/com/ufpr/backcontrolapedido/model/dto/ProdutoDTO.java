package com.ufpr.backcontrolapedido.model.dto;

import com.ufpr.backcontrolapedido.model.entities.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String descricao;

    public ProdutoDTO(Produto entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
    }

}
