package com.nubank.clientmanager.controller;

import com.nubank.clientmanager.controller.dto.request.ClienteRequest;
import com.nubank.clientmanager.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ClientController {


    private final ClienteService clienteService;

    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(path = "/clientes")
    public ResponseEntity<?>  cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest, UriComponentsBuilder uriBuilder) {
        var novoCliente = clienteService.cadastrarCliente(clienteRequest);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoCliente.id()).toUri();
        return ResponseEntity.created(uri).body(novoCliente);
    }

    @GetMapping(path = "/clientes")
    public ResponseEntity<?> listarClientes() {
        var clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<?> buscarCliente(@Valid @PathVariable Long id) {
        var cliente = clienteService.obterCliente(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado" ));
        return ResponseEntity.ok(cliente);
    }
}
