package com.mercado.livre.perguntas;

import javax.validation.constraints.NotNull;

public class PerguntaProdutoRequest {
    @NotNull
    private String titulo;

    public String getTitulo() {
        return titulo;
    }
}
