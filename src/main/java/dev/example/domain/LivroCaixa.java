package dev.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class LivroCaixa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    public LivroCaixa() {
    }

    public LivroCaixa(Integer id, Date dataLancamento, String descricao, String tipo, Double valor) {
        this.id = id;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public LivroCaixa(Integer id, Date dataLancamento, String descricao, String tipo, @NotNull Double valor, Cliente cliente) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroCaixa that = (LivroCaixa) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
