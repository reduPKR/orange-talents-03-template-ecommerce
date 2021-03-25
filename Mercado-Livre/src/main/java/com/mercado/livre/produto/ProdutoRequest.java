package com.mercado.livre.produto;

import com.mercado.livre.categoria.Categoria;
import com.mercado.livre.produto.caracteristica.Caracteristica;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    @Size(min = 0)
    private double preco;
    @NotNull
    @NotEmpty
    @Size(min = 0)
    private double estoque;
    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private String descricao;
    @NotNull
    private Categoria categoria;
    @OneToMany
    @Size(min = 3)
    private List<Caracteristica> caracteristicas;

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public double getEstoque() {
        return estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }
}
