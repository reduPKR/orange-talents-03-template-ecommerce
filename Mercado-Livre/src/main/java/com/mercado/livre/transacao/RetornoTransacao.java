package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;

public interface RetornoTransacao {
    Transacao converterTransacao(Compra compra);
}
