package dev.example.clientes.service;

import dev.example.domain.Cliente;
import dev.example.exceptions.ObjectNotFoundException;
import dev.example.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        return obj;
    }

    public Cliente update(Cliente obj) {
        find(obj.getId());
        return clienteRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException dive) {
            throw new DataIntegrityViolationException("Erro ao Excluir objeto");
        }
    }

//    public List<Cliente> findByNomeCpfCnpjCidadeUf(String nome, String cpfcnpj, String cidade, String uf) {
//        List<Cliente> obj;
//        if()
//        if(!nome.equals("") && !cpfcnpj.equals("")){ obj = clienteRepository.findByNomeAndCpf_CnpjContaining(nome, cpfcnpj);  }
//        else if(!email.equals("")) { obj = clienteRepository.findByEmailContaining(email); }
//        else { obj = clienteRepository.findAll(); }
//
//        return obj;
//    }
    
}
