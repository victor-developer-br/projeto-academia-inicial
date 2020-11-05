package dev.example.domain.dto;

import dev.example.domain.Cliente;
import dev.example.domain.LivroCaixa;

import java.util.Date;

public class LivroCaixaWithClienteDTO extends LivroCaixa {

    private Integer clienteId;

    public LivroCaixaWithClienteDTO() {
    }

    public LivroCaixaWithClienteDTO(Integer id, Date dataLancamento, String descricao, String tipo, Double valor, Integer clienteId) {
        super(id, dataLancamento, descricao, tipo, valor);
        this.clienteId = clienteId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
