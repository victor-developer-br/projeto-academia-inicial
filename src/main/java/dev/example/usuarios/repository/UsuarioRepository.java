package dev.example.usuarios.repository;

import dev.example.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByLoginAndSenha(String login, String senha);
    List<Usuario> findByNomeContaining(String nome);
    List<Usuario> findDistinctByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(String nome, String email);
    List<Usuario> findByEmailContaining(String email);

}
