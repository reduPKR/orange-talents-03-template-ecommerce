package com.mercado.livre.produto;

import com.mercado.livre.categoria.Categoria;
import com.mercado.livre.produto.caracteristica.Caracteristica;
import com.mercado.livre.produto.caracteristica.CaracteristicaRepository;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private double preco;
    @Column(nullable = false)
    private double estoque;
    @Column(nullable = false)
    @Size(max = 1000)
    private String descricao;
    @OneToMany
    private List<Caracteristica> caracteristicas;
    @ManyToOne
    private Categoria categoria;

    public Produto() {
    }

    public Produto(String nome, @Size(min = 0) double preco, @Size(min = 0) double estoque, @Size(max = 1000) String descricao, Categoria categoria, List<Caracteristica> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
    }

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

    public boolean validarCaracteristica(CaracteristicaRepository caracteristicaRepository) {
        boolean retorno = true;

        Caracteristica item;
        for(int i = 0; i < caracteristicas.size(); i++){
            item = caracteristicas.get(i);

            Optional<Caracteristica> busca = caracteristicaRepository
                    .findByNomeAndDescricao(item.getNome(), item.getDescricao());

            if(busca.isPresent()){
                /*Altera o id*/
                caracteristicas.get(i).setId(busca.get().getId());
            }else
                retorno = false;
        }
        return retorno;
    }
}
