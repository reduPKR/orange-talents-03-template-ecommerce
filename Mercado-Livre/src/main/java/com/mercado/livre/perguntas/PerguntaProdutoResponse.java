package com.mercado.livre.perguntas;

import com.mercado.livre.usuario.Usuario;

import java.time.LocalDateTime;

public class PerguntaProdutoResponse {
    private String titulo;
    private LocalDateTime criacao;
    private String usuario;

    public PerguntaProdutoResponse(PerguntaProduto perguntaProduto) {
        this.titulo = perguntaProduto.getTitulo();
        this.criacao = perguntaProduto.getCriacao();
        this.usuario = perguntaProduto.getUsuario().getUsername();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public String getUsuario() {
        return usuario;
    }
}
