package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private long transacaoGatewayId;
    @Column(nullable = false)
    private LocalDateTime instante;
    @ManyToOne
    private Compra compra;

    public Transacao(String status, long transacaoGatewayId, LocalDateTime instante, Compra compra) {
        this.status = status;
        this.transacaoGatewayId = transacaoGatewayId;
        this.instante = instante;
        this.compra = compra;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public long getTransacaoGatewayId() {
        return transacaoGatewayId;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Compra getCompra() {
        return compra;
    }
}
