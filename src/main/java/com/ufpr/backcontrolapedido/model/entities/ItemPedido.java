package com.ufpr.backcontrolapedido.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false, referencedColumnName = "id")
    private Produto produto;
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
}
