package com.mercado.livre.opiniao;

import com.mercado.livre.produto.Produto;
import com.mercado.livre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class OpiniaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(1)
    @Max(5)
    private short nota;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    @Length(max = 500)
    private String descricao;
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Usuario usuario;
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Produto produto;

    public OpiniaoProduto() {
    }

    public OpiniaoProduto(@Min(1) @Max(5) short nota, String titulo, @Length(max = 500) String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public long getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
