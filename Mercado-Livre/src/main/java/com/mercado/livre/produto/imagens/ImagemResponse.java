package com.mercado.livre.produto.imagens;

import java.util.Set;
import java.util.stream.Collectors;

public class ImagemResponse {
    private Set<String> link;

    public ImagemResponse(Set<ImagemProduto> imagens) {
        link = imagens.stream()
                .map(imagem -> imagem.getLink())
                .collect(Collectors.toSet());
    }

    public Set<String> getLink() {
        return link;
    }
}
