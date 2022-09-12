package com.ufpr.backcontrolapedido.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private Long cpf;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "sobrenome", nullable = false, length = 70)
    private String sobrenome;

}
