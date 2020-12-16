package dev.example.usuarios.api;

import dev.example.domain.Usuario;
import dev.example.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerApi {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id)
    {
        Usuario obj = usuarioService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Usuario>> findByNomeOrEmail(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                     @RequestParam(value = "email", defaultValue = "") String email)
    {

        //List<Usuario> obj = usuarioService.findByNomeOrEmail(nome, email); // com JPA Trabalhando....
        List<Usuario> obj = usuarioService.findByStreamUsers(nome, email); // Com Stream .....
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam(value = "login") String login,
                                         @RequestParam(value = "senha") String senha)
    {
        String checkUser = usuarioService.login(login, senha);
        return ResponseEntity.ok().body(checkUser);
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@Valid @RequestBody Usuario usuario)
    {
        Usuario obj =  usuarioService.insert(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @Valid @RequestBody Usuario usuario)
    {
        usuarioService.find(id);
        usuario.setId(id);
        usuarioService.update(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Integer id)
    {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
