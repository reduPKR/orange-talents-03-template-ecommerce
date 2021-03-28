package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String status;
    @NotNull
    private long transacaoGatewayId;
    @ManyToOne
    private Compra compra;

    public long getId() {
        return id;
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
