package com.ufpr.backcontrolapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.backcontrolapedido.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsClienteByCpf(Long cpf);
}
