package com.mercado.livre.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroNoCadastroException extends RuntimeException{
    private String erro;
    public ErroNoCadastroException(String message, String erro) {
        super(message);
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
}
