package com.nubank.clientmanager.controller.dto.response;

import lombok.Builder;

@Builder
public record ClienteResponse(String nome, long id) {
}
