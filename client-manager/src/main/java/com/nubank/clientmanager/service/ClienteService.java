package com.nubank.clientmanager.service;

import com.nubank.clientmanager.controller.dto.request.ContatoRequest;
import com.nubank.clientmanager.controller.dto.response.ClienteResponse;
import com.nubank.clientmanager.controller.dto.request.ClienteRequest;
import com.nubank.clientmanager.controller.dto.response.ContatoResponse;
import com.nubank.clientmanager.exception.ClienteNaoEncontradoException;
import com.nubank.clientmanager.repository.ClienteRepository;
import com.nubank.clientmanager.repository.entity.ClienteEntity;
import com.nubank.clientmanager.repository.entity.ContatoEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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
                .map(cliente -> ClienteResponse.builder()
                        .id(cliente.getId())
                        .nome(cliente.getNome())
                        .build());
    }

    public void adicionarContatoAoCliente(Long id, ContatoRequest contato) {
         var cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
         cliente.adicionarContato(
                 ContatoEntity.builder()
                         .cliente(cliente)
                         .nome(contato.nome())
                         .email(contato.email())
                         .telefone(contato.telefone())
                         .build());
         clienteRepository.save(cliente);
        }

    public ArrayList<ContatoResponse> listarContatos(Long clienteId){
        var cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));

        return cliente.getContatos().stream().map(clienteEntity ->
                ContatoResponse.builder()
                        .id(clienteEntity.getId())
                        .nome(clienteEntity.getNome())
                        .email(clienteEntity.getEmail())
                        .telefone(clienteEntity.getTelefone())
                        .build()).collect(Collectors.toCollection(ArrayList::new));

    }

    }
