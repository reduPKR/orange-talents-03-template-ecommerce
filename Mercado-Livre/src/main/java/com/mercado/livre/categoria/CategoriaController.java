package com.mercado.livre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid  @RequestBody CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.converter(categoriaRepository);
        categoriaRepository.save(categoria);
        if (categoria.getId() != 0)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
