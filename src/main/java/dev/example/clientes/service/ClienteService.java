package dev.example.clientes.service;

import dev.example.domain.Cliente;
import dev.example.exceptions.ObjectNotFoundException;
import dev.example.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public List<Cliente> findByCriteria(String nome, String cpfcnpj, String cidade, String uf)
    {
        final List<Specification<Cliente>> specifications = new ArrayList<>();

        if(StringUtils.hasText(nome))
        {
            specifications.add((root, criteriaQuery, cb) -> cb.like(cb.upper(root.get("nome")), '%' + nome.toUpperCase() + '%'));
        }
        if(StringUtils.hasText(cpfcnpj))
        {
            specifications.add((root, criteriaQuery, cb) -> cb.like(cb.upper(root.get("cpfcnpj")), '%' +  cpfcnpj.toUpperCase() + '%'));
        }
        if(StringUtils.hasText(cidade))
        {
            specifications.add((root, criteriaQuery, cb) -> cb.like(cb.upper(root.get("cidade")), '%' + cidade.toUpperCase() + '%' ));
        }
        if(StringUtils.hasText(uf))
        {
            specifications.add((root, criteriaQuery, cb) -> cb.like(cb.upper(root.get("uf")), '%' + uf.toUpperCase() + '%'));
        }
        Specification<Cliente> finishedQuery = null;
        for(final Specification<Cliente> specification : specifications)
        {
            if(finishedQuery == null)
            {
                finishedQuery = specification;
            } else {
                finishedQuery = Specification.where(finishedQuery).and(specification);
            }
        }

        return clienteRepository.findAll(finishedQuery);
    }
    
}
