package com.mercado.livre.categoria;

import com.mercado.livre.validador.UniqueValue;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CategoriaRequest {
    @NotBlank
    @NotNull
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @OneToMany
    private long categoria_mae_id;

    public String getNome() {
        return nome;
    }

    public long getCategoria_mae_id() {
        return categoria_mae_id;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {
        return new Categoria(nome, getCategoriaMaeEntidade(categoriaRepository));
    }

    private Categoria getCategoriaMaeEntidade(CategoriaRepository categoriaRepository) {
        Optional<Categoria> optional = categoriaRepository.findById(categoria_mae_id);
        if(optional.isPresent())
            return optional.get();
        return null;
    }
}
