package com.nubank.clientmanager.controller;

import com.nubank.clientmanager.controller.dto.error.ErrorResponse;
import com.nubank.clientmanager.controller.dto.request.ClienteRequest;
import com.nubank.clientmanager.controller.dto.request.ContatoRequest;
import com.nubank.clientmanager.controller.dto.response.ClienteResponse;
import com.nubank.clientmanager.controller.dto.response.ContatoResponse;
import com.nubank.clientmanager.exception.ClienteNaoEncontradoException;
import com.nubank.clientmanager.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name="Cliente", description="Controlador para gerenciamento de usuários")
public class ClientController {

    private final ClienteService clienteService;
    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary="Salva um novo cliente", description = "Faz o cadastro de novos usuários no sistema")
    @ApiResponse(responseCode = "201",
            description = "Cliente salvo com sucesso",
            content = {
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClienteResponse.class)
                    )
            })
    @ApiResponse(responseCode =  "400", description = "Parametros informados são inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno ao processar a mensagem")
    public ResponseEntity<?>  cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest, UriComponentsBuilder uriBuilder) {
        var novoCliente = clienteService.cadastrarCliente(clienteRequest);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoCliente.id()).toUri();
        return ResponseEntity.created(uri).body(novoCliente);
    }

    @GetMapping
    @Operation(summary="Listagem de clientes", description = "Retorna a lista com todos os clientes cadastrados")
    @ApiResponse(responseCode = "200",
            description = "Lista com os clientes",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ClienteResponse.class))
                    )
            })
    @ApiResponse(responseCode = "500", description = "Erro interno ao processar a mensagem")
    public ResponseEntity<?> listarClientes() {
        var clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary="Obtem um cliente pelo id", description = "Faz a busca de um cliente pelo identificador")
    @ApiResponse(responseCode = "200",description = "Cliente encontrado",content = {
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClienteResponse.class)
            )
    })
    @ApiResponse(responseCode = "400", description = "Parametros informados são inválidos")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    ),

            })
    @ApiResponse(responseCode = "500", description = "Erro interno ao processar a mensagem")
    public ResponseEntity<?> buscarCliente(@Valid @PathVariable Long id) {
        var cliente = clienteService.obterCliente(id)
                .orElseThrow(()-> new ClienteNaoEncontradoException("Cliente não encontrado" ));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping(path="/{id}/contatos")
    @Operation(summary="Salva um novo contato", description = "Faz o cadastro de um novo contato para o cliente informado")
    @ApiResponse(responseCode = "201",
            description = "Contato salvo com sucesso",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            })
    @ApiResponse(responseCode = "400", description = "Parametros informados são inválidos")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    ),

            })
    @ApiResponse(responseCode = "500", description = "Erro interno ao processar a mensagem")
    public ResponseEntity<?>  adicionarContato(@Valid @PathVariable Long id, @Valid @RequestBody ContatoRequest contatoRequest,  UriComponentsBuilder uriBuilder) {
            clienteService.adicionarContatoAoCliente(id, contatoRequest);
            var uri = uriBuilder.path("/clientes/{id}/contatos").buildAndExpand(id).toUri();
            return ResponseEntity.created(uri).build();

    }

    @GetMapping(path="/{id}/contatos")
    @Operation(summary="Contatos de um cliente", description = "Exibe a listagem de contatos por cliente")
    @ApiResponse(responseCode = "200",
            description = "Listagem de contatos",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            })
    @ApiResponse(responseCode = "400", description = "Parametros informados são inválidos")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ContatoResponse.class))
                    ),

            })
    @ApiResponse(responseCode = "500", description = "Erro interno ao processar a mensagem")
    public ResponseEntity<?>  listarContatos(@Valid @PathVariable Long id,  UriComponentsBuilder uriBuilder) {
        var contatos = clienteService.listarContatos(id);
        return ResponseEntity.ok().body(contatos);

    }

}
