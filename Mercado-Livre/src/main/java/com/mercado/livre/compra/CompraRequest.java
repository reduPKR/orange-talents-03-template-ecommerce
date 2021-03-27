package com.mercado.livre.compra;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CompraRequest {
    @Min(1)
    private int qtdeCompra;
    @NotNull
    private Gateway gateway;
    @NotNull
    private long idComprador;
    @NotNull
    private long idProduto;

    public int getQtdeCompra() {
        return qtdeCompra;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public long getIdComprador() {
        return idComprador;
    }

    public long getIdProduto() {
        return idProduto;
    }
}
