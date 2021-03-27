package com.mercado.livre.perguntas;


import com.mercado.livre.produto.Produto;
import com.mercado.livre.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class PerguntaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column
    private LocalDateTime criacao;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Produto produto;

    public PerguntaProduto(String titulo, LocalDateTime criacao, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.criacao = criacao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "titulo=" + titulo + "\n" +
                "produto=" + produto.getNome()+"\n" +
                "criacao=" + criacao ;
    }
}
