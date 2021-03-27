package com.mercado.livre.produto;

import com.mercado.livre.categoria.Categoria;
import com.mercado.livre.produto.caracteristica.Caracteristica;
import com.mercado.livre.produto.caracteristica.CaracteristicaRepository;
import com.mercado.livre.produto.imagens.ImagemProduto;
import com.mercado.livre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    public Produto() {
    }

    public Produto(Usuario dono, String nome, @Size(min = 0) double preco, @Size(min = 0) double estoque, @Size(max = 1000) String descricao, Categoria categoria, List<Caracteristica> caracteristicas) {
        this.dono = dono;
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

    public String getDescricao() {
        return descricao;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public void associarImagens(Set<String> links){
        Set<ImagemProduto> novasImagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(novasImagens);
    }
}
