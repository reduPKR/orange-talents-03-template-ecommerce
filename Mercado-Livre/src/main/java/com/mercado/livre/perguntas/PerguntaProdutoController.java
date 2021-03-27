package com.mercado.livre.perguntas;

import com.mercado.livre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/pergunta/produto")
public class PerguntaProdutoController {
    @PostMapping("/{id}")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PerguntaProdutoRequest perguntaRequest,
                                       @PathVariable("id") long id,
                                       @AuthenticationPrincipal Usuario usuario,
                                       BindingResult result){


    }
}
