package com.ufpr.backcontrolapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.backcontrolapedido.model.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
