package com.mercado.livre.produto.imagens;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake {
    public static Set<String> send(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://arquivo.io/"+imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
