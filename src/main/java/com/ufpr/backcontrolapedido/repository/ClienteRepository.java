package com.ufpr.backcontrolapedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.backcontrolapedido.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsClienteByCpf(String cpf);

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByNome(String nome);
}
