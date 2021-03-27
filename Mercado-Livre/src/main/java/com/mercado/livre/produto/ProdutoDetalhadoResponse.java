package com.mercado.livre.produto;

import com.mercado.livre.opiniao.OpiniaoProduto;
import com.mercado.livre.opiniao.OpniaoProdutoResponse;
import com.mercado.livre.perguntas.PerguntaProduto;
import com.mercado.livre.perguntas.PerguntaProdutoResponse;
import com.mercado.livre.produto.caracteristica.CaracteristicaResponse;

import java.util.List;
import java.util.Set;

public class ProdutoDetalhadoResponse {
    private String nome;
    private double preco;
    private String descricao;
    private double media;
    private long qtdeNotas;
    private Set<String> links;
    private Set<CaracteristicaResponse> caracteristicas;
    private Set<OpniaoProdutoResponse> opinioes;
    private Set<PerguntaProdutoResponse> perguntas;

    public ProdutoDetalhadoResponse(Produto produto, OpiniaoProduto opiniaoProduto, PerguntaProduto perguntaProduto) {
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMedia() {
        return media;
    }

    public long getQtdeNotas() {
        return qtdeNotas;
    }

    public Set<String> getLinks() {
        return links;
    }

    public Set<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<OpniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaProdutoResponse> getPerguntas() {
        return perguntas;
    }
}
