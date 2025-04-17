package com.nubank.clientmanager.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(description = "Estrutura utilizada para exibir contatos de um cliente")
public record ContatoResponse(
        @Schema(description = "Nome do contato")
        @NotBlank String nome,
        @Schema(description = "E-mail do contato (não obrigatório)")
        @Email String email,
        @Schema(description = "Telefone do contato")
        @NotBlank String telefone,
        @Schema(description = "Identificador do contato")
        @NotNull Long id
        )
{
}