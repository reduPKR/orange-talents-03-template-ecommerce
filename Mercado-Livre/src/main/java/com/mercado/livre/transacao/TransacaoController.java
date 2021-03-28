package com.mercado.livre.transacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping
public class TransacaoController {
    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> cadastroPaypal(){

    }
}
