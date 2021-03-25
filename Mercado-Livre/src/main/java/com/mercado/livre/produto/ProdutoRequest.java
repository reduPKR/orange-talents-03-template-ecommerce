package com.mercado.livre.produto;

import com.mercado.livre.produto.caracteristica.Caracteristica;

import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @Min(0)
    private double preco;
    @NotNull
    @Min(0)
    private double estoque;
    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private String descricao;
    @NotNull
    private Long categoria_id;
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

    public Long getCategoria() {
        return categoria_id;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }
}
