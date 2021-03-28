package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;
import com.mercado.livre.compra.CompraRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class PagSeguroRequest {
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @NotNull
    private long compraId;

    public StatusEnum getStatus() {
        return status;
    }

    public long getCompraId() {
        return compraId;
    }

    public Transacao converter(long transacaoGatewayId, CompraRepository compraRepository) {
        Optional<Compra> compra = compraRepository.findById(compraId);
        if(compra.isPresent())
            return new Transacao(
                    status.toString(),
                    transacaoGatewayId,
                    LocalDateTime.now(),
                    compra.get()
            );

        return null;
    }
}
