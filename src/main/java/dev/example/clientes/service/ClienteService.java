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

    public List<Cliente> findAllCritiria(String nome, String cpfcnpj, String cidade, String uf) {

        List<Cliente> obj;
        if(!nome.equals("") && !cpfcnpj.equals("") && cidade.equals("") && uf.equals(""))
        {
            obj = clienteRepository.findByNomeContainingIgnoreCaseAndCpfcnpjContaining(nome, cpfcnpj);
        }
        else if(nome.equals("") && !cpfcnpj.equals("") && !cidade.equals("") && uf.equals(""))
        {
            obj = clienteRepository.findByCpfcnpjContainingAndCidadeContainingIgnoreCase(cpfcnpj, cidade);
        }
        else if (nome.equals("") && cpfcnpj.equals("") && !cidade.equals("") && !uf.equals(""))
        {
            obj = clienteRepository.findByCidadeContainingIgnoreCaseAndUfContainingIgnoreCase(cidade, uf);
        }
        else
        {
            String search = nome + cpfcnpj + cidade + uf;
            obj = clienteRepository.findAllCriteria(search);
        }

        return obj;
    }
    
}
