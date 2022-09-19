package com.ufpr.backcontrolapedido.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufpr.backcontrolapedido.model.dto.ClienteDTO;
import com.ufpr.backcontrolapedido.service.ClienteService;

@CrossOrigin("*") // mecanismo que permite que recursos restritos sejam recuperados por outro
                  // domínio (anotação para uma classe específica)
@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseEntity<List<ClienteDTO>> getClientes() {
        List<ClienteDTO> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<ClienteDTO> getClienteByCpf(@PathVariable Long cpf) {
        ClienteDTO cliente = clienteService.findByCpf(String.format("%011d", cpf));
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<ClienteDTO> getClienteByCpf(@PathVariable String nome) {
        ClienteDTO cliente = clienteService.findByNome(nome);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> registerCliente(@RequestBody ClienteDTO cliente) {
        cliente = clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        cliente = clienteService.update(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
