package dev.example.clientes.service;

import dev.example.domain.Cliente;
import dev.example.exceptions.ObjectNotFoundException;
import dev.example.clientes.repository.ClienteRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    //// PARTE DE RELATORIO DOS CLIENTES UTILIZANDO JASPER REPORT.
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException
    {
        String path = "C:\\Users\\antonio.v.tenorio\\Desktop\\report";
        List<Cliente> clientes = clienteRepository.findAll();
        //carrega o arquivo e compila ele.
        File file = ResourceUtils.getFile("classpath:clientes.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Antonio Tenorio");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\clientes.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\clientes.pdf");
        }
        return "Relatório foi gerado na pasta: " + path;
    }
}
