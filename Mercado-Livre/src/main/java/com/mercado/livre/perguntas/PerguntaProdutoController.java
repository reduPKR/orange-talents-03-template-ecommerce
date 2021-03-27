package com.mercado.livre.perguntas;

import com.mercado.livre.exception.ErroResponse;
import com.mercado.livre.perguntas.email.EmailFake;
import com.mercado.livre.produto.Produto;
import com.mercado.livre.produto.ProdutoRepository;
import com.mercado.livre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pergunta/produto")
public class PerguntaProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaProdutoRepository perguntaProdutoRepository;

    @Autowired
    private EmailFake emailFake;

    @PostMapping("/{id}")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PerguntaProdutoRequest perguntaRequest,
                                       @PathVariable("id") long id,
                                       @AuthenticationPrincipal Usuario usuario,
                                       BindingResult result){

        if(!result.hasErrors()){
            Optional<Produto> produto = produtoRepository.findById(id);

            if(produto.isPresent()){
                PerguntaProduto pergunta = perguntaRequest.converter(usuario, produto.get());

                perguntaProdutoRepository.save(pergunta);
                if(pergunta.getId() != 0){
                    String destino = produto.get().getDono().getUsername();
                    String remetente = usuario.getUsername();

                    emailFake.send(destino, remetente, pergunta);
                    return ResponseEntity.ok().build();
                }else
                    result.addError(new FieldError("Pergunta", "Pergunta sobre o produto","Erro ao salvar os dados"));
            }else
                result.addError(new FieldError("Pergunta", "Pergunta sobre o produto","Produto não encontrado"));
        }

          List<ErroResponse> erro = result.getFieldErrors()
                .stream()
                .map(ErroResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(erro);
    }
}
