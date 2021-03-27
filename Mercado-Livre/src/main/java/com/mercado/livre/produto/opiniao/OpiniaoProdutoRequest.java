package com.mercado.livre.produto.opiniao;

import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OpiniaoProdutoRequest {
    @Min(1)
    @Max(5)
    private short nota;
    @NotNull
    private String titulo;
    @NotNull
    @Length(max = 500)
    private String descricao;
    @NotNull
    @ManyToOne
    private long idUsuario;
    @NotNull
    @ManyToOne
    private long idProduto;

    public short getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public long getIdProduto() {
        return idProduto;
    }
}
