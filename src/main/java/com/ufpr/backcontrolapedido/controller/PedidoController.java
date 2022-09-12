package com.ufpr.backcontrolapedido.controller;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufpr.backcontrolapedido.model.dto.PedidoDTO;
import com.ufpr.backcontrolapedido.service.PedidoService;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @GetMapping
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseEntity<List<PedidoDTO>> getPedidos() {
        List<PedidoDTO> list = pedidoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> registerPedido(@RequestBody PedidoDTO pedido) {
        Date date = new Date();
        pedido.setData(dateFormat.format(date));
        pedido = pedidoService.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(pedido.getId())
                .toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDTO> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
