package com.ufpr.backcontrolapedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.backcontrolapedido.model.Cliente;
import com.ufpr.backcontrolapedido.repository.ClienteRepository;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente registerCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    // @PutMapping("/{id}")
    // @Transactional
    // public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody
    // @Valid AtualizarCursoForm form) {
    // Optional<Cliente> optional = clienteRepository.findById(id);
    // if (optional.isPresent()) {
    // Cliente cliente = form.atualizar(id, clienteRepository);
    // return ResponseEntity.ok(cliente);
    // }
    // return ResponseEntity.notFound().build();
    // }

}
