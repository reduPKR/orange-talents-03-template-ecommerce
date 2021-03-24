package com.mercado.livre.categoria;

import com.mercado.livre.validador.UniqueValue;

import javax.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @OneToMany
    @Column(nullable = true)
    private Categoria categoriaMae;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
