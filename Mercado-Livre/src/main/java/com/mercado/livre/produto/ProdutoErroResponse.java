package com.mercado.livre.produto;

import org.springframework.validation.FieldError;

public class ProdutoErroResponse {
    private String campo;
    private String erro;

    public ProdutoErroResponse(FieldError fieldError) {
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
