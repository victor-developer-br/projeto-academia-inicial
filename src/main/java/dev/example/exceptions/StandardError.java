package dev.example.exceptions;

public class StandardError  {

    private Long ts;
    private Integer status;
    private String mensagem;
    private String caminho;
    private String erros;

    public StandardError(Long ts, Integer status, String mensagem, String caminho, String erros) {
        this.ts = ts;
        this.status = status;
        this.mensagem = mensagem;
        this.caminho = caminho;
        this.erros = erros;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getErros() {
        return erros;
    }

    public void setErros(String erros) {
        this.erros = erros;
    }
}
