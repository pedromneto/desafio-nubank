package com.nubank.clientmanager.service;

import com.nubank.clientmanager.controller.dto.response.ClienteResponse;
import com.nubank.clientmanager.controller.dto.request.ClienteRequest;
import com.nubank.clientmanager.repository.ClienteRepository;
import com.nubank.clientmanager.repository.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {


    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest) {
        var novoCliente = clienteRepository.save(ClienteEntity.builder()
                        .nome(clienteRequest.nome())
                        .build());

        return ClienteResponse.builder()
                .nome(novoCliente.getNome())
                .id(novoCliente.getId())
                .build();
    }

    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(client ->
                        ClienteResponse.builder()
                        .nome(client.getNome())
                        .id(client.getId())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<ClienteResponse> obterCliente(Long id) {
        return clienteRepository.findById(id)
                .map(cliente ->{
                    return ClienteResponse.builder()
                            .id(cliente.getId())
                            .nome(cliente.getNome())
                            .build();
                });
    }

}
