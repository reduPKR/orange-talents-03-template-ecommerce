package com.mercado.livre.produto.imagens;

import com.mercado.livre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;
    @URL
    @NotBlank
    private String link;

    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return link;
    }
}
