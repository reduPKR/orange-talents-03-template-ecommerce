package com.mercado.livre.compra;

import com.mercado.livre.produto.Produto;
import com.mercado.livre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(1)
    private int qtdeCompra;
    @Column
    private double valorAtual;
    @Enumerated(EnumType.STRING)
    private Gateway gateway = Gateway.PAYPAL;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario comprador;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Produto produto;

    public long getId() {
        return id;
    }

    public int getQtdeCompra() {
        return qtdeCompra;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProduto() {
        return produto;
    }
}

