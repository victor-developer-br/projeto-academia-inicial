package dev.example.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCadastro;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 30, message = "Este campo deve conter um máximo de 30 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 15, message = "Este campo deve conter um máximo de 15 caracteres")
    @Column(unique = true)
    private String login;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 10, message = "Este campo deve conter um máximo de 10 caracteres")
    private String senha;


    @Length(max = 11, message = "Este campo deve conter um máximo de 11 caracteres")
    private String telefone;

    private String email;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 1, message = "Este campo deve conter um máximo de 1 caracter")
    private String perfil;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(max = 1, message = "Este campo deve conter um máximo de 1 caracter")
    private String status;

    public Usuario() {
    }

    public Usuario(Integer id, Date dataCadastro, String nome, String login, String senha,
                   String telefone, String email, String perfil, String status) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.perfil = perfil;
        this.status = status;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
