package com.ufpr.backcontrolapedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.backcontrolapedido.model.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsClienteByCpf(String cpf);

    Optional<Cliente> findByCpf(Long cpf);
}
