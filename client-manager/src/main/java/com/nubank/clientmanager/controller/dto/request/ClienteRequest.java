package com.nubank.clientmanager.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "Estrutura para persistência de novos clientes")
public record ClienteRequest(
        @Schema(description = "Nome do cliente a ser salvo")
        @NotBlank(message = "Campo obrigatório") String nome) {
}
