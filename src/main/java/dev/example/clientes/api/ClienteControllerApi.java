package dev.example.clientes.api;

import dev.example.domain.Cliente;
import dev.example.clientes.service.ClienteService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.FileNotFoundException;
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

    @GetMapping("/search")
    public ResponseEntity<List<Cliente>> findAllCritiria(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                         @RequestParam(value = "cpfcnpj", defaultValue = "") String cpfcnpj,
                                                         @RequestParam(value = "cidade", defaultValue = "") String cidade,
                                                         @RequestParam(value = "uf", defaultValue = "") String uf
                                                         )
    {

        //List<Cliente> obj = clienteService.findAllCritiria(nome, cpfcnpj, cidade, uf); //usando JPA da forma simples.

        List<Cliente> obj = clienteService.findByCriteria(nome, cpfcnpj, cidade, uf);  // Usando Specification para fazer a busca.

        return ResponseEntity.ok().body(obj);
    }


    @GetMapping("/report/{format}")
    public String gerarRelatorioCliente(@PathVariable String format) throws FileNotFoundException, JRException
    {
        return clienteService.exportReport(format);
    }

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
