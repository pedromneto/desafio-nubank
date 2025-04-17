package com.nubank.clientmanager.controller.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Estrutura para respostas de erro da API")
public record ErrorResponse(
        @Schema(description = "Mensagem de erro legível por humanos.", example = "Cliente não encontrado com ID: 123")
        String message,
        @Schema(description = "Objeto de dados adicional relacionado ao erro (opcional).")
        Object details
) {

    public ErrorResponse(String message) {
        this(message, null);
    }


}
