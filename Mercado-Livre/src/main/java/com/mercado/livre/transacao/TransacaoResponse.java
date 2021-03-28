package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;

public class TransacaoResponse {
    private String status;
    private long transacaoGatewayId;
    private Compra compra;

    public TransacaoResponse(String status, long transacaoGatewayId, Compra compra) {
        this.status = status;
        this.transacaoGatewayId = transacaoGatewayId;
        this.compra = compra;
    }

    public String getStatus() {
        return status;
    }

    public long getTransacaoGatewayId() {
        return transacaoGatewayId;
    }

    public Compra getCompra() {
        return compra;
    }
}
