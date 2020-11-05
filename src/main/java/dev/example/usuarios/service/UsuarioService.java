package dev.example.usuarios.service;

import dev.example.domain.Cliente;
import dev.example.domain.Usuario;
import dev.example.exceptions.ObjectNotFoundException;
import dev.example.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario find(Integer id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Transactional
    public Usuario insert(Usuario obj) {
        obj.setId(null);
        obj = usuarioRepository.save(obj);
        return obj;
    }

    public Usuario update(Usuario obj) {
        find(obj.getId());
        return usuarioRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException dive) {
            throw new DataIntegrityViolationException("Erro ao Excluir objeto");
        }
    }

    public String login(String login, String senha) {
        Usuario obj = usuarioRepository.findByLoginAndSenha(login, senha);
        if (obj == null) {
            return "Usuário ou senha inválida";
        }
        if(obj.getStatus().equals("C"))
        {
            return "Usuario está inativado no sistema";
        }
        return "Usuario " + obj.getNome() + " Foi Logado com Sucesso";
    }

    public List<Usuario> findByNomeOrEmail(String nome, String email) {
        List<Usuario> obj;
        if(!nome.equals("")){ obj = usuarioRepository.findByNomeContaining(nome);  }
        else if(!email.equals("")) { obj = usuarioRepository.findByEmailContaining(email); }
        else { obj = usuarioRepository.findAll(); }

        return obj;
    }

}
