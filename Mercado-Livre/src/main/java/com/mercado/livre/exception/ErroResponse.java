package com.mercado.livre.exception;

import org.springframework.validation.FieldError;

public class ErroResponse {
    private String campo;
    private String erro;

    public ErroResponse(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.erro = fieldError.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
