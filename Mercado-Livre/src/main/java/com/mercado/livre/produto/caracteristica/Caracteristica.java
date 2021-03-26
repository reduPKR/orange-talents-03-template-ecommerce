package com.mercado.livre.produto.caracteristica;

import javax.persistence.*;

@Entity
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;

    public Caracteristica() {
    }

    public Caracteristica(CaracteristicaRequest caracteristicaRequest) {
        this.nome = caracteristicaRequest.getNome();
        this.descricao = caracteristicaRequest.getDescricao();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
