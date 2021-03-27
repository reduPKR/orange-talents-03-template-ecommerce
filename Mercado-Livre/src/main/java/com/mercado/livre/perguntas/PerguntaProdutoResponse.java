package com.mercado.livre.perguntas;

import com.mercado.livre.usuario.Usuario;

import java.time.LocalDateTime;

public class PerguntaProdutoResponse {
    private String titulo;
    private LocalDateTime criacao;
    private Usuario usuario;

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
