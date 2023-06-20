package br.ufpr.tads.univan.auth.dto;

public record CadastrarRequest(
        String primeiroNome,
        String sobrenome,
        String email,
        String senha
) {
}
