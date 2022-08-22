package com.ufpr.backcontrolapedido.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufpr.backcontrolapedido.model.dto.ProdutoDTO;
import com.ufpr.backcontrolapedido.model.entities.Produto;
import com.ufpr.backcontrolapedido.repository.ProdutoRepository;
import com.ufpr.backcontrolapedido.service.exception.ResourceNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // recupera todos os produtos
    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
    }

    // recupera produto por id específico
    public ProdutoDTO findById(Long id) {
        Produto entity = getEntityById(id);
        return new ProdutoDTO(entity);
    }

    // insere produto no banco de dados
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copyDtoToEntity(dto, entity);
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    // atualiza produto no banco de dados
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Produto entity = getEntityById(id);
        copyDtoToEntityForUpdate(dto, entity);
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    // deleta produto por id
    public void delete(Long id) {
        getEntityById(id);
        produtoRepository.deleteById(id);
    }

    // método encapsulado para recuperar entidade produto por id do banco de dados
    private Produto getEntityById(Long id) {
        Optional<Produto> result = produtoRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
    }

    // método encapsulado que transfere objeto DTO (Data transfer object) para a
    // entidade que terá contato com banco de dados diretamente
    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setDescricao(dto.getDescricao());
    }

    private void copyDtoToEntityForUpdate(ProdutoDTO dto, Produto entity) {
        entity.setDescricao(dto.getDescricao());
    }
}
