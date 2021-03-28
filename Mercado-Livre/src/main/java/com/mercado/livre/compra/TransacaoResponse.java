package com.mercado.livre.compra;

import com.mercado.livre.compra.Compra;

public class TransacaoResponse {
    private String status;
    private long transacaoGatewayId;
    private long compraId;

    public TransacaoResponse(String status, long transacaoGatewayId, Compra compra) {
        this.status = status;
        this.transacaoGatewayId = transacaoGatewayId;
        this.compraId = compra.getId();
    }

    public String getStatus() {
        return status;
    }

    public long getTransacaoGatewayId() {
        return transacaoGatewayId;
    }

    public long getCompra() {
        return compraId;
    }
}
