package com.nubank.clientmanager.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Estrutura utilizada para adicionar novos contatos a um cliente")
public record ContatoRequest(
        @Schema(description = "Nome do contato")
        @NotBlank String nome,
        @Schema(description = "E-mail do contato (não obrigatório)")
        @Email String email,
        @Schema(description = "Telefone do contato")
        @NotBlank String telefone) {
}
