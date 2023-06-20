package br.ufpr.tads.univan.auth.repository;

import br.ufpr.tads.univan.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByEmail(String email);
}
