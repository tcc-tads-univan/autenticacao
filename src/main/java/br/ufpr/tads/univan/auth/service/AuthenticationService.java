package br.ufpr.tads.univan.auth.service;

import br.ufpr.tads.univan.auth.dto.AuthenticationRequest;
import br.ufpr.tads.univan.auth.dto.AuthenticationResponse;
import br.ufpr.tads.univan.auth.dto.CadastrarRequest;
import br.ufpr.tads.univan.auth.model.Perfil;
import br.ufpr.tads.univan.auth.model.Usuario;
import br.ufpr.tads.univan.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse cadastrar(CadastrarRequest request) {
        var usuario = Usuario.builder()
                .primeiroNome(request.primeiroNome())
                .sobrenome(request.sobrenome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .perfil(Perfil.ALUNO)
                .build();

        repository.save(usuario);
        var jwtToken = jwtService.gerarToken(usuario);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse autenticar(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );
        var usuario = repository.findUsuarioByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.gerarToken(usuario);

        return new AuthenticationResponse(jwtToken);
    }
}
