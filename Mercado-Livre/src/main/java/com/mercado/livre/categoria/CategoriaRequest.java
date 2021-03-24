package com.mercado.livre.categoria;

import com.mercado.livre.validador.UniqueValue;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
