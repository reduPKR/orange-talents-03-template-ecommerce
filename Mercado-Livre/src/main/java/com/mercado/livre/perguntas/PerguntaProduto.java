package com.mercado.livre.perguntas;


import com.mercado.livre.produto.Produto;
import com.mercado.livre.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class PerguntaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column
    private LocalTime criacao;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Produto produto;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalTime getCriacao() {
        return criacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
