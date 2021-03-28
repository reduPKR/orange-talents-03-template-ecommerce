package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;
import com.mercado.livre.compra.CompraRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class PaypalRequest {
    @Min(0)
    @Max(1)
    private long status;
    @NotNull
    private long compraId;

    public long getStatus() {
        return status;
    }

    public long getCompraId() {
        return compraId;
    }

    public Transacao converter(long transacaoGatewayId, CompraRepository compraRepository) {
        Optional<Compra> compra = compraRepository.findById(compraId);
        if(compra.isPresent())
            return new Transacao(
                    status+"",
                    transacaoGatewayId,
                    LocalDateTime.now(),
                    compra.get()
            );

        return null;
    }
}
