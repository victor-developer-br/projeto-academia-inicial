package dev.example.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errosLista = new ArrayList<>();

    public ValidationError(Long ts, Integer status, String mensagem, String caminho, String erros) {
        super(ts, status, mensagem, caminho, erros);
    }

    public List<FieldMessage> getErrosLista() {
        return errosLista;
    }

    public void addErros(String fieldName, String message)
    {
        errosLista.add(new FieldMessage(fieldName, message));
    }
}
