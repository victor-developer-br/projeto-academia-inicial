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
import java.util.stream.Collectors;

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
        if(!email.equals("") && !nome.equals("")) { obj = usuarioRepository.findDistinctByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(nome, email); }
        else if(!email.equals("")) { obj = usuarioRepository.findByEmailContaining(email); }
        else if(!nome.equals("")){ obj = usuarioRepository.findByNomeContaining(nome);  }
        else { obj = usuarioRepository.findAll(); }
        return obj;
    }

    public List<Usuario> findByStreamUsers(String nome, String email)
    {
        List<Usuario> obj = usuarioRepository.findAll();

        if(!email.equals("") && !nome.equals("")) { obj = obj.stream()
                .filter(n -> n.getNome().toUpperCase().contains(nome.toUpperCase()) &&
                        n.getEmail().toUpperCase().contains(email.toUpperCase()))
                .collect(Collectors.toList()); }

        else if  (!nome.equals("")){
            obj = obj.stream().filter(f -> f.getNome().toUpperCase().contains(nome.toUpperCase())).collect(Collectors.toList());
        }

        else if (!email.equals(""))
        {
            obj = obj.stream().filter(f -> f.getEmail().toUpperCase().contains(email.toUpperCase())).collect(Collectors.toList());
        }
        else
        {
            return obj;
        }

        return obj;
    }

}
