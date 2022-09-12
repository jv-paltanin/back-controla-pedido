package com.ufpr.backcontrolapedido.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufpr.backcontrolapedido.model.dto.ClienteDTO;
import com.ufpr.backcontrolapedido.model.entities.Cliente;
import com.ufpr.backcontrolapedido.repository.ClienteRepository;
import com.ufpr.backcontrolapedido.service.exception.ResourceAlreadyExistsException;
import com.ufpr.backcontrolapedido.service.exception.ResourceNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // recupera todos os clientes
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    }

    // recupera cliente por id específico
    public ClienteDTO findById(Long id) {
        Cliente entity = getEntityById(id);
        return new ClienteDTO(entity);
    }

    // insere cliente no banco de dados
    public ClienteDTO insert(ClienteDTO dto) {
        if (!clienteRepository.existsClienteByCpf(dto.getCpf())) {
            Cliente entity = new Cliente();
            copyDtoToEntity(dto, entity);
            entity = clienteRepository.save(entity);
            return new ClienteDTO(entity);
        } else {
            throw new ResourceAlreadyExistsException("CPF " + dto.getCpf() + " já existe!");
        }
    }

    // atualiza cliente no banco de dados
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente entity = getEntityById(id);
        copyDtoToEntityForUpdate(dto, entity);
        entity = clienteRepository.save(entity);
        return new ClienteDTO(entity);
    }

    // deleta cliente por id
    public void delete(Long id) {
        getEntityById(id);
        clienteRepository.deleteById(id);
    }

    // método encapsulado para recuperar entidade cliente por id do banco de dados
    private Cliente getEntityById(Long id) {
        Optional<Cliente> result = clienteRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
    }

    // método encapsulado que transfere objeto DTO (Data transfer object) para a
    // entidade que terá contato com banco de dados diretamente
    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
    }

    private void copyDtoToEntityForUpdate(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
    }

}
