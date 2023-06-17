package br.ufpr.tads.univan.auth.auth;

public record CadastrarRequest(
        String primeiroNome,
        String sobrenome,
        String email,
        String senha
) {
}
