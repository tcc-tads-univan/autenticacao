package br.ufpr.tads.univan.auth.auth;

import br.ufpr.tads.univan.auth.config.JwtService;
import br.ufpr.tads.univan.auth.usuario.Role;
import br.ufpr.tads.univan.auth.usuario.Usuario;
import br.ufpr.tads.univan.auth.usuario.UsuarioRepository;
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
                .role(Role.ALUNO)
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
