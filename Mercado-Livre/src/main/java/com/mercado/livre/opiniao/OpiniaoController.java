package com.mercado.livre.opiniao;

import com.mercado.livre.exception.ErroResponse;
import com.mercado.livre.produto.Produto;
import com.mercado.livre.produto.ProdutoRepository;
import com.mercado.livre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/opiniao/produto")
public class OpiniaoController {
    @Autowired
    private OpiniaoProdutoRepository opiniaoProdutoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody OpiniaoProdutoRequest opiniaoRequest,
                                       @PathVariable long id,
                                       @AuthenticationPrincipal Usuario usuario,
                                       BindingResult result){

        if(!result.hasErrors()){
            Optional<Produto> produto =  produtoRepository.findById(id);

            if(produto.isPresent()){
                OpiniaoProduto opiniao = opiniaoRequest.converter(usuario, produto.get());
                opiniaoProdutoRepository.save(opiniao);

                if(opiniao.getId() != 0)
                    return ResponseEntity.ok().build();
                else
                    result.addError(new FieldError("Opinião", "erro ao cadastrar", "Erro ao cadastrar a opinião"));
            }else
                result.addError(new FieldError("Opinião", "produto não encontrado", "Produto não encontrado no sistema"));
        }

        List<ErroResponse> listarErros = result.getFieldErrors()
                    .stream()
                    .map(ErroResponse::new)
                    .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(listarErros);
    }
}
