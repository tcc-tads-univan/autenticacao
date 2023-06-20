package br.ufpr.tads.univan.auth.dto;

public record CadastrarRequest(
        String nome,
        String cpf,
        String email,
        String senha
) {
}
