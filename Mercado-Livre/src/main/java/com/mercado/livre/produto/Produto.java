package com.mercado.livre.produto;

import com.mercado.livre.categoria.Categoria;
import com.mercado.livre.produto.caracteristica.Caracteristica;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Size(min = 0)
    private double preco;
    @Column(nullable = false)
    @Size(min = 0)
    private double estoque;
    @Column(nullable = false)
    @Size(max = 1000)
    private String descricao;
    @OneToMany
    private List<Caracteristica> caracteristicas;
    @ManyToOne
    private Categoria categoria;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public double getEstoque() {
        return estoque;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
