package dev.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCadastro;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 30, message = "Este campo deve conter um máximo de 30 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 14, message = "Este campo deve conter um máximo de 14 caracteres")
    private String cpf_Cnpj;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 50, message = "Este campo deve conter um máximo de 50 caracteres")
    private String logradouro;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 40, message = "Este campo deve conter um máximo de 40 caracteres")
    private String cidade;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 2, message = "Este campo deve conter um máximo de 2 caracteres")
    private String uf;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 8, message = "Este campo deve conter um máximo de 8 caracteres")
    private String cep;

    private String telefone;
    private String email;

    public Cliente() {
    }

    public Cliente(Integer id, Date dataCadastro, String nome, String cpf_Cnpj, String logradouro, String cidade, String uf, String cep, String telefone, String email) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.cpf_Cnpj = cpf_Cnpj;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public void setCpf_Cnpj(String cpf_Cnpj) {
        this.cpf_Cnpj = cpf_Cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
