package br.ufpr.tads.univan.auth.controller;

import br.ufpr.tads.univan.auth.dto.AuthenticationRequest;
import br.ufpr.tads.univan.auth.dto.AuthenticationResponse;
import br.ufpr.tads.univan.auth.dto.CadastrarRequest;
import br.ufpr.tads.univan.auth.model.Perfil;
import br.ufpr.tads.univan.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/aluno")
    public ResponseEntity<AuthenticationResponse> cadastrarAluno(@RequestBody CadastrarRequest request) {
        return ResponseEntity.ok(service.cadastrar(request, Perfil.ALUNO));
    }

    @PostMapping("/motorista")
    public ResponseEntity<AuthenticationResponse> cadastrarMotorista(@RequestBody CadastrarRequest request) {
        return ResponseEntity.ok(service.cadastrar(request, Perfil.MOTORISTA));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AuthenticationResponse> autenticar(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.autenticar(request));
    }
}
