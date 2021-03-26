package com.mercado.livre.produto;

import com.mercado.livre.categoria.Categoria;
import com.mercado.livre.categoria.CategoriaRepository;
import com.mercado.livre.produto.caracteristica.Caracteristica;
import com.mercado.livre.produto.caracteristica.CaracteristicaRequest;
import org.springframework.validation.BindingResult;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @Min(0)
    private double preco;
    @NotNull
    @Min(0)
    private int estoque;
    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    private long categoria;//Verificar por que quando coloca id da erro
    @OneToMany
    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas;

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getCategoria() {
        return categoria;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.categoria);
        if(categoria.isPresent())
            return  new Produto(
                    nome,
                    preco,
                    estoque,
                    descricao,
                    categoria.get(),
                    caracteristicas.stream().map(Caracteristica::new).collect(Collectors.toList())
            );
        throw new RuntimeException("Categoria não cadastrada");
    }
}
