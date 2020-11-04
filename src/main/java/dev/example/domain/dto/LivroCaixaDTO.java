package dev.example.domain.dto;

import dev.example.domain.LivroCaixa;

import java.util.Date;

public class LivroCaixaDTO extends LivroCaixa {
    private Double saldo;

    public LivroCaixaDTO(Double saldo) {
        this.saldo = saldo;
    }

    public LivroCaixaDTO(Integer id, Date dataLancamento, String descricao, String tipo, Double valor, Double saldo) {
        super(id, dataLancamento, descricao, tipo, valor);
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
