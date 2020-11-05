package dev.example.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.example.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class LivroCaixaDTOInsert {
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataLancamento;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 50, message = "Este campo deve conter um máximo de 50 caracteres")
    private String descricao;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 1, message = "Este campo deve conter um máximo de 1 caracter")
    private String tipo;

    @NotNull
    private Double valor;


    private Integer cliente;

    public LivroCaixaDTOInsert() {
    }

    public LivroCaixaDTOInsert(Integer id, Date dataLancamento, String descricao,  String tipo, Double valor, Integer cliente) {
        this.id = id;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}
