package com.mercado.livre.ranking;

import org.springframework.stereotype.Component;

@Component
public class RankingFake {
    public void adicionarVenda(long compraId, long vendedorId) {
        System.out.println("Vendedor de id->"+vendedorId+" Realizou mais uma venda, cod: "+compraId);
    }
}
