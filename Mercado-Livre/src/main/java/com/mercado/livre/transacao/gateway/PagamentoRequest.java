package com.mercado.livre.transacao.gateway;

import com.mercado.livre.compra.CompraRepository;
import com.mercado.livre.transacao.Transacao;
import com.mercado.livre.transacao.TransacaoRepository;

public interface PagamentoRequest {
    Transacao converter(long transacaoGatewayId, CompraRepository compraRepository, TransacaoRepository transacaoRepository);
}
