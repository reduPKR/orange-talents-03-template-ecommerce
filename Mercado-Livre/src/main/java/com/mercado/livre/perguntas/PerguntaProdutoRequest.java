package com.mercado.livre.perguntas;

import com.mercado.livre.produto.Produto;
import com.mercado.livre.usuario.Usuario;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class PerguntaProdutoRequest {
    @NotNull
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public PerguntaProduto converter(Usuario usuario, Produto produto) {
        return new PerguntaProduto(
                titulo,
                LocalDateTime.now(),
                usuario,
                produto
        );
    }
}
