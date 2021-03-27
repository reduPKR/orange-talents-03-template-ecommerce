package com.mercado.livre.produto;

import com.mercado.livre.opiniao.OpiniaoProduto;
import com.mercado.livre.opiniao.OpniaoProdutoResponse;
import com.mercado.livre.perguntas.PerguntaProduto;
import com.mercado.livre.perguntas.PerguntaProdutoResponse;
import com.mercado.livre.produto.caracteristica.Caracteristica;
import com.mercado.livre.produto.caracteristica.CaracteristicaResponse;
import com.mercado.livre.produto.imagens.ImagemResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalhadoResponse {
    private String nome;
    private double preco;
    private String descricao;
    private String media;
    private long qtdeNotas;
    private ImagemResponse links;
    private Set<CaracteristicaResponse> caracteristicas;
    private Set<OpniaoProdutoResponse> opinioes;
    private Set<PerguntaProdutoResponse> perguntas;

    public ProdutoDetalhadoResponse(Produto produto, Optional<List<OpiniaoProduto>> opiniaoProduto, Optional<List<PerguntaProduto>> perguntaProduto) {
        ajusteProduto(produto);
        ajusteOpiniao(opiniaoProduto);
        ajustePerguntas(perguntaProduto);
    }

    private void ajustePerguntas(Optional<List<PerguntaProduto>> perguntaProduto) {
        if(perguntaProduto.isPresent()){
            this.perguntas = perguntaProduto.get().stream()
                            .map(item -> new PerguntaProdutoResponse(item))
                            .collect(Collectors.toSet());
        }else{
            this.perguntas = null;
        }
    }

    private void ajusteOpiniao(Optional<List<OpiniaoProduto>> opiniaoProduto) {
        if(opiniaoProduto.isPresent()){
            this.opinioes = converterOpiniao(opiniaoProduto.get());
            this.qtdeNotas = this.opinioes.size();
            this.media = calcularMedia();
        }else{
            this.media = "Não houve notas";
            this.qtdeNotas = 0;
            this.opinioes = null;
        }
    }

    private String calcularMedia() {
        double total = 0;
        long qtde = 0;

        for (OpniaoProdutoResponse item: opinioes){
            qtde++;
            total += item.getNota();
        }

        if(qtde == 0)
            return "1";
        else
            return String.format("%.1f",total/qtde);
    }

    private Set<OpniaoProdutoResponse> converterOpiniao(List<OpiniaoProduto> opiniaoProduto) {
        return opiniaoProduto.stream()
                .map(item -> new OpniaoProdutoResponse(item))
                .collect(Collectors.toSet());
    }

    private void ajusteProduto(Produto produto){
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.links = new ImagemResponse(produto.getImagens());

        this.caracteristicas = converterCaracteristicas(produto.getCaracteristicas());

    }

    private Set<CaracteristicaResponse> converterCaracteristicas(List<Caracteristica> caracteristicas) {
        return caracteristicas.stream()
                .map(item -> new CaracteristicaResponse(item))
                .collect(Collectors.toSet());
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

    public String getMedia() {
        return media;
    }

    public long getQtdeNotas() {
        return qtdeNotas;
    }

    public ImagemResponse getLinks() {
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
