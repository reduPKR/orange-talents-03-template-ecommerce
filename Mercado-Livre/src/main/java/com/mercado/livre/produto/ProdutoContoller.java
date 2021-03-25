package com.mercado.livre.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoContoller {
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest){
        return ResponseEntity.badRequest().build();
    }
}
