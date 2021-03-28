package com.mercado.livre.notaFiscal;

import org.springframework.stereotype.Component;

@Component
public class NotaFiscalFake {

    public void gerarNota(long compraId, long compradorId) {
        System.out.println("Gerando noda da compra "+compraId+" para o cliente "+compradorId);
    }
}
