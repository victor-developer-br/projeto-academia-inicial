package dev.example.livrocaixa.api;

import dev.example.domain.LivroCaixa;
import dev.example.domain.dto.LivroCaixaDTOInsert;
import dev.example.domain.dto.LivroCaixaWithClienteDTO;
import dev.example.livrocaixa.service.LivroCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livrocaixa")
public class LivroCaixaControllerApi {
    @Autowired
    private LivroCaixaService livroCaixaService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroCaixaWithClienteDTO> findById(@PathVariable Integer id)
    {
        LivroCaixaWithClienteDTO obj = livroCaixaService.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping("/clienteid/{id}")
    public ResponseEntity<List<LivroCaixaWithClienteDTO>> findByClienteId(@PathVariable Integer id)
    {
        List<LivroCaixa> obj = livroCaixaService.findByClienteId(id);
        List<LivroCaixaWithClienteDTO> livroCaixaWithClienteDTOList = livroCaixaService.fromDTOWithCliente(obj);
        return ResponseEntity.ok().body(livroCaixaWithClienteDTOList);
    }

    @PostMapping
    public ResponseEntity<LivroCaixa> insert(@Valid @RequestBody LivroCaixaDTOInsert livroCaixa)
    {
        LivroCaixa obj =  livroCaixaService.insert(livroCaixa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroCaixa> update(@PathVariable Integer id, @Valid @RequestBody LivroCaixaDTOInsert livroCaixa)
    {
        livroCaixaService.find(id);
        livroCaixaService.update(livroCaixa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroCaixa> delete(@PathVariable Integer id)
    {
        livroCaixaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
