package br.ufpr.tads.univan.auth.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<AuthenticationResponse> cadastrar(@RequestBody CadastrarRequest request) {
        return ResponseEntity.ok(service.cadastrar(request));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AuthenticationResponse> autenticar(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.autenticar(request));
    }
}
