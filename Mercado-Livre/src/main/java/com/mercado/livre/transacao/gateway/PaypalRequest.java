package com.mercado.livre.transacao.gateway;

import com.mercado.livre.compra.Compra;
import com.mercado.livre.compra.CompraRepository;
import com.mercado.livre.transacao.Transacao;
import com.mercado.livre.transacao.TransacaoRepository;
import com.mercado.livre.validador.UniqueValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class PaypalRequest implements PagamentoRequest{
    @Min(0)
    @Max(1)
    private long status;
    @NotNull
    @UniqueValue(domainClass = Transacao.class, fieldName = "status = 'SUCESSO' and compra_id", message = "Esta compra já foi finalizada com sucesso")
    private long compraId;

    public long getStatus() {
        return status;
    }

    public long getCompraId() {
        return compraId;
    }

    public Transacao converter(long transacaoGatewayId, CompraRepository compraRepository, TransacaoRepository transacaoRepository) {
        Optional<Transacao> transacao = transacaoRepository.findSucesso(transacaoGatewayId);
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
