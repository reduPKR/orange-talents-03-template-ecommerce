package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;
import com.mercado.livre.compra.CompraRepository;
import com.mercado.livre.validador.UniqueValue;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class PagSeguroRequest {
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @NotNull
    @UniqueValue(domainClass = Transacao.class, fieldName = "status = 'SUCESSO' and compra_id", message = "Esta compra já foi finalizada com sucesso")
    private long compraId;//Validar se já nao existe no banco de dados com sucesso

    public StatusEnum getStatus() {
        return status;
    }

    public long getCompraId() {
        return compraId;
    }

    public Transacao converter(long transacaoGatewayId, CompraRepository compraRepository, TransacaoRepository transacaoRepository) {
        //Tem que validar o transacaoGatewayId, esse id nao pode aparecer 2 vezes com sucesso.
        Optional<Transacao> transacao = transacaoRepository.findSucesso(transacaoGatewayId);
        Optional<Compra> compra = compraRepository.findById(compraId);

        if(compra.isPresent() && transacao.isEmpty())
            return new Transacao(
                    status.toString(),
                    transacaoGatewayId,
                    LocalDateTime.now(),
                    compra.get()
            );

        return null;
    }
}
