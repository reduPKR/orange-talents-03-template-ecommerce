package com.mercado.livre.compra;

import com.mercado.livre.perguntas.PerguntaProduto;
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
    @ManyToOne(cascade = CascadeType.MERGE)
    private Usuario comprador;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Produto produto;

    public Compra() {
    }

    public Compra(@Min(1) int qtdeCompra,  Gateway gateway, Usuario comprador, Produto produto) {
        this.qtdeCompra = qtdeCompra;
        this.valorAtual = produto.getPreco();
        this.gateway = gateway;
        this.comprador = comprador;
        this.produto = produto;
    }

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

    public boolean validarEstoque() {
        return qtdeCompra <= produto.getEstoque();
    }

    public void abaterEstoque() {
        produto.movimentarEstoque(-qtdeCompra);
    }

    public void getRota() {
    }
}

