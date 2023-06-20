package br.ufpr.tads.univan.auth.dto;

public record AuthenticationRequest(
        String email,
        String senha
) {
}
