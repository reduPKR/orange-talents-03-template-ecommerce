package com.mercado.livre.compra;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class CompraResponse {
    private String url1;
    private URI url2;
    private CompraResponseToTransacao transacaoResponse;

    public CompraResponse(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        montaUrl(compra, uriComponentsBuilder);
        criarTransacao(compra);
    }

    private void criarTransacao(Compra compra) {
        transacaoResponse = new CompraResponseToTransacao(
                getStatusSucesso(),
                compra
        );
    }

    private String getStatusSucesso() {
        if(url1.contains("paypal"))
            return "1";
        else
            return "SUCESSO";
    }

    private String getStatusFalha() {
        if(url1.contains("paypal"))
            return "0";
        else
            return "ERRO";
    }

    private void montaUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        String auxiliar = compra.getGateway().getUrl1();
        url1 = auxiliar.replace("{idCompra}",String.valueOf(compra.getId()));

        auxiliar = compra.getGateway().getUrl2();
        url2 = uriComponentsBuilder.path(auxiliar).buildAndExpand(compra.getId()).toUri();
    }

    public String getUrl1() {
        return url1;
    }

    public URI getUrl2() {
        return url2;
    }

    public CompraResponseToTransacao getTransacaoResponse() {
        return transacaoResponse;
    }
}
