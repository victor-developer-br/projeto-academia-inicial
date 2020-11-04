package dev.example.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContabilDTO {

    private Integer id;
    private String nome;
    private String cpf_cnpj;
    private String telefone;
    private List<LivroCaixaDTO> contabil = new ArrayList<>();

    public ContabilDTO() {
    }

    public ContabilDTO(Integer id, String nome, String cpf_cnpj, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf_cnpj = cpf_cnpj;
        this.telefone = telefone;
    }

    public ContabilDTO(Integer id, String nome, String cpf_cnpj, String telefone, List<LivroCaixaDTO> contabil) {
        this.id = id;
        this.nome = nome;
        this.cpf_cnpj = cpf_cnpj;
        this.telefone = telefone;
        this.contabil = contabil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<LivroCaixaDTO> getContabil() {
        return contabil;
    }

    public void setContabil(List<LivroCaixaDTO> contabil) {
        this.contabil = contabil;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContabilDTO contabil = (ContabilDTO) o;
        return id.equals(contabil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
