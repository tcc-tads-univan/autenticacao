package br.ufpr.tads.univan.auth.auth;

public record AuthenticationRequest(
        String email,
        String senha
) {
}
