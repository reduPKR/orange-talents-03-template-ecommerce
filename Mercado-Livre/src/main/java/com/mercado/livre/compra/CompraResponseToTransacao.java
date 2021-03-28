package com.mercado.livre.compra;

import com.mercado.livre.compra.Compra;

public class CompraResponseToTransacao {
    private String status;
    private long compraId;

    public CompraResponseToTransacao(String status, Compra compra) {
        this.status = status;
        this.compraId = compra.getId();
    }

    public String getStatus() {
        return status;
    }

    public long getCompra() {
        return compraId;
    }
}
