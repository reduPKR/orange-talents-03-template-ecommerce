package com.mercado.livre.produto.caracteristica;

import javax.persistence.Column;

public class CaracteristicaResponse {
    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
