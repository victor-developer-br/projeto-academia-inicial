package dev.example.contabil.service;

import dev.example.Utils.Utilities;
import dev.example.clientes.repository.ClienteRepository;
import dev.example.domain.Cliente;
import dev.example.domain.LivroCaixa;
import dev.example.domain.dto.ContabilDTO;
import dev.example.domain.dto.LivroCaixaDTO;
import dev.example.exceptions.ObjectNotFoundException;
import dev.example.livrocaixa.repository.LivroCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContabilService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroCaixaRepository livroCaixaRepository;

    public ContabilDTO contabilById(Integer id)
    {
        //criando obj vazio para implementação
        ContabilDTO filtro = new ContabilDTO();

        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                ()->  new ObjectNotFoundException("Erro Cliente não encontrado " + id + ", tipo: " + Cliente.class.getName()));
        List<LivroCaixa> livroCaixa = livroCaixaRepository.findByClienteId(cliente.getId());
        filtro.setId(cliente.getId());
        filtro.setNome(cliente.getNome());
        filtro.setCpf_cnpj(cliente.getCpf_Cnpj());
        filtro.setTelefone(cliente.getTelefone());

        List<LivroCaixaDTO> livroCaixaDTOS = fromLivroCaixaDTO(livroCaixa);

        for(LivroCaixaDTO lv : livroCaixaDTOS) {
            filtro.getContabil().add(lv);
        }

        return filtro;
    }

    public ContabilDTO contabilByIdAndDate(Integer id, String start, String end)
    {
        //criando obj vazio para implementação
        ContabilDTO filtro = new ContabilDTO();

        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                ()->  new ObjectNotFoundException("Erro Cliente não encontrado " + id + ", tipo: " + Cliente.class.getName()));

        Date startDate = Utilities.ConvertDate(start);
        Date endDate = Utilities.ConvertDate(end);

        List<LivroCaixa> livroCaixa = livroCaixaRepository.findByDataLancamentoBetweenAndClienteId(startDate, endDate, cliente.getId());
        filtro.setId(cliente.getId());
        filtro.setNome(cliente.getNome());
        filtro.setCpf_cnpj(cliente.getCpf_Cnpj());
        filtro.setTelefone(cliente.getTelefone());

        List<LivroCaixaDTO> livroCaixaDTOS = fromLivroCaixaDTO(livroCaixa);

        for(LivroCaixaDTO lv : livroCaixaDTOS) {
            filtro.getContabil().add(lv);
        }

        return filtro;
    }

    public List<LivroCaixaDTO> fromLivroCaixaDTO(List<LivroCaixa> dto)
    {
        List<LivroCaixaDTO> livroCaixaDTOList = new ArrayList<>();
        Double somatotal = 0.0;

        for(LivroCaixa lv : dto)
        {
            if (lv.getTipo().equals("C"))
            {
               somatotal = somatotal + lv.getValor();
                LivroCaixaDTO livroCaixaDTO = new LivroCaixaDTO(lv.getId(), lv.getDataLancamento(), lv.getDescricao(), lv.getTipo(),
                        lv.getValor(), somatotal);
                livroCaixaDTOList.add(livroCaixaDTO);
            }
            else
            {
                somatotal = somatotal - lv.getValor();
                LivroCaixaDTO livroCaixaDTO = new LivroCaixaDTO(lv.getId(), lv.getDataLancamento(), lv.getDescricao(), lv.getTipo(),
                        lv.getValor(), somatotal);
                livroCaixaDTOList.add(livroCaixaDTO);
            }

        }

        return livroCaixaDTOList;
    }
}
