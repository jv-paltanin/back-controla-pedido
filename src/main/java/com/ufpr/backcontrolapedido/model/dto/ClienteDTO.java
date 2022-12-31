package com.ufpr.backcontrolapedido.model.dto;

import com.ufpr.backcontrolapedido.model.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;

    public ClienteDTO(Cliente entity) {
        this.id = entity.getId();
        this.cpf = entity.getCpf();
        this.nome = entity.getNome();
        this.sobrenome = entity.getSobrenome();
    }

}