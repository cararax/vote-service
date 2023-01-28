package com.carara.vote.infra.client.response;

import java.io.Serializable;

public record AssociateResponse(Long id, String cpf, String email) implements Serializable {
}