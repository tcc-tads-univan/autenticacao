package br.ufpr.tads.univan.auth.controller;

import br.ufpr.tads.univan.auth.repository.UsuarioRepository;
import br.ufpr.tads.univan.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
    final JwtService jwtService;
    @GetMapping
    public ResponseEntity<String> helloName(@RequestHeader(value = "Authorization") String token, String name) {
        if (name != null && name.trim().length() > 0) {
            return ResponseEntity.ok("Olá, " + name);
        }
        return ResponseEntity.ok("Olá, " + jwtService.extrairUsername(token.substring(7)));
    }
}
