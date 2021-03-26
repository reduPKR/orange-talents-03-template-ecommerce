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
    @OneToMany
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

    public Usuario getDono() {
        return dono;
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

    public List<Caracteristica> salvarCaracteristicas(CaracteristicaRepository caracteristicaRepository) {
        Caracteristica item;
        for(int i = 0; i < caracteristicas.size(); i++){
            item = caracteristicas.get(i);

            Optional<Caracteristica> busca = caracteristicaRepository
                    .findByNomeAndDescricao(item.getNome(), item.getDescricao());

            if(busca.isEmpty()){
                caracteristicaRepository.save(item);
                caracteristicas.get(i).setId(item.getId());
            }else{
                caracteristicas.get(i).setId(busca.get().getId());
            }
        }

        return caracteristicas;
    }

    public boolean validarCaracteristica() {
        boolean retorno = true;

        for (Caracteristica item : caracteristicas) {
            if (item.getId() == 0) {
                retorno = false;
            }
        }
        return retorno;
    }

    public void associarImagens(Set<String> links){
        Set<ImagemProduto> novasImagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(novasImagens);
    }
}
