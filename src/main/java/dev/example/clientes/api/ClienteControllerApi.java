package dev.example.clientes.api;

import dev.example.domain.Cliente;
import dev.example.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControllerApi {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id)
    {
        Cliente obj = clienteService.find(id);
        return ResponseEntity.ok().body(obj);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Cliente>> findByNomeOrEmail(@RequestParam(value = "nome", defaultValue = "") String nome,
//                                                           @RequestParam(value = "email", defaultValue = "") String email)
//    {
//
//        List<Cliente> obj = clienteService.findByNomeOrEmail(nome, email);
//        return ResponseEntity.ok().body(obj);
//    }


    @PostMapping
    public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente)
    {
        Cliente obj =  clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @Valid @RequestBody Cliente cliente)
    {
        clienteService.find(id);
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Integer id)
    {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
