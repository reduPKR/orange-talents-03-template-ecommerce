package com.mercado.livre.transacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping
public class TransacaoController {
    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> cadastroPaypal(@PathVariable long id,
                                            @RequestBody PaypalRequest paypalRequest){

        return ResponseEntity.badRequest().build();
    }
}
