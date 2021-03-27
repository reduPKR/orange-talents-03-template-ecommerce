package com.mercado.livre.opiniao;


public class OpniaoProdutoResponse {
    private short nota;
    private String titulo;
   private String descricao;

    public OpniaoProdutoResponse(OpiniaoProduto opiniaoProduto) {
        this.nota = opiniaoProduto.getNota();
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
    }

    public short getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
