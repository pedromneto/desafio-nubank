package com.nubank.clientmanager.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ClienteRequest(@NotBlank(message = "Campo obrigatório") String nome) {
}
