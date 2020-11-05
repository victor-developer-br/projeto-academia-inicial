package dev.example.livrocaixa.service;

import dev.example.clientes.repository.ClienteRepository;
import dev.example.domain.Cliente;
import dev.example.domain.LivroCaixa;
import dev.example.domain.dto.LivroCaixaDTOInsert;
import dev.example.domain.dto.LivroCaixaWithClienteDTO;
import dev.example.exceptions.ObjectNotFoundException;
import dev.example.livrocaixa.repository.LivroCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroCaixaService {

    @Autowired
    private LivroCaixaRepository livroCaixaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public LivroCaixaWithClienteDTO find(Integer id) {
        Optional<LivroCaixa> obj = livroCaixaRepository.findById(id);
        if(obj.isPresent())
        {
            return new LivroCaixaWithClienteDTO(obj.get().getId(), obj.get().getDataLancamento(), obj.get().getDescricao(), obj.get().getTipo(), obj.get().getValor(), obj.get().getCliente().getId());
        }
        else
        {
            throw new ObjectNotFoundException("LivroCaixa não encontrado: " + id + ", Tipo: " + LivroCaixaWithClienteDTO.class.getName());
        }
    }

    public List<LivroCaixa> findByClienteId(Integer id) {
        List<LivroCaixa> obj = livroCaixaRepository.findByClienteId(id);
        if(obj == null)
        {
            throw new ObjectNotFoundException("LivroCaixa não encontrado: " + id + ", Tipo: " + LivroCaixa.class.getName());
        }
        return obj;
    }

    @Transactional
    public LivroCaixa insert(LivroCaixaDTOInsert obj) {
        Cliente cli = clienteRepository.findById(obj.getCliente()).orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado"));
        LivroCaixa livroCaixa = new LivroCaixa(null, obj.getDataLancamento(), obj.getDescricao(), obj.getTipo(), obj.getValor(), cli);
        livroCaixa = livroCaixaRepository.save(livroCaixa);
        return livroCaixa;
    }
    

    public LivroCaixa update(LivroCaixaDTOInsert obj) {
        Cliente cli = clienteRepository.findById(obj.getCliente()).orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado"));
        LivroCaixa livroCaixa = new LivroCaixa(obj.getId(), obj.getDataLancamento(), obj.getDescricao(), obj.getTipo(), obj.getValor(), cli);
        return livroCaixaRepository.save(livroCaixa);
    }

    public void delete(Integer id) {
        find(id);
        try {
            livroCaixaRepository.deleteById(id);
        } catch (DataIntegrityViolationException dive) {
            throw new DataIntegrityViolationException("Erro ao Excluir objeto");
        }
    }

    public List<LivroCaixaWithClienteDTO> fromDTOWithCliente(List<LivroCaixa> obj)
    {
        List<LivroCaixaWithClienteDTO> newDTO = new ArrayList<>();
        for(LivroCaixa lc : obj)
        {
            LivroCaixaWithClienteDTO actLivroCaixa = new LivroCaixaWithClienteDTO(lc.getId(), lc.getDataLancamento(), lc.getDescricao(), lc.getTipo(), lc.getValor(), lc.getCliente().getId());
            newDTO.add(actLivroCaixa);
        }
        return newDTO;
    }

//    public List<LivroCaixa> findByNomeCpfCnpjCidadeUf(String nome, String cpfcnpj, String cidade, String uf) {
//        List<LivroCaixa> obj;
//        if()
//        if(!nome.equals("") && !cpfcnpj.equals("")){ obj = livroCaixaRepository.findByNomeAndCpf_CnpjContaining(nome, cpfcnpj);  }
//        else if(!email.equals("")) { obj = livroCaixaRepository.findByEmailContaining(email); }
//        else { obj = livroCaixaRepository.findAll(); }
//
//        return obj;
//    }
}
