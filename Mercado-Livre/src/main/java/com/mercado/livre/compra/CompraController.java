package com.mercado.livre.compra;

import com.mercado.livre.email.EmailFake;
import com.mercado.livre.exception.ErroResponse;
import com.mercado.livre.perguntas.PerguntaProduto;
import com.mercado.livre.produto.Produto;
import com.mercado.livre.produto.ProdutoRepository;
import com.mercado.livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compra/produto")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EmailFake emailFake;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CompraRequest compraRequest,
                                       BindingResult result,
                                       UriComponentsBuilder uriComponentsBuilder){
        if(!result.hasErrors()){
            Compra compra = compraRequest.converter(usuarioRepository, produtoRepository);
            if(ValidarCompra(compra)){
                compra.abaterEstoque();

                PerguntaProduto email = GerarEmailCompra(compra);
                emailFake.send(
                        compra.getProduto().getDono().getUsername(),
                        compra.getComprador().getUsername(),
                        email
                        );

                compraRepository.save(compra);
                if (compra.getId() != 0) {
                    String rota = compra.getRota(uriComponentsBuilder);
                    return ResponseEntity.status(HttpStatus.FOUND).body(rota);
                }
            }
            result.addError(new FieldError("Compra", "Erro ao finalizar", "Ocorreu um erro ao finalizar a compra"));
        }

        List<ErroResponse> erros = result.getFieldErrors()
                .stream()
                .map(ErroResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    private PerguntaProduto GerarEmailCompra(Compra compra) {
        return new PerguntaProduto(
                "Olá gostaria de comprar "+compra.getQtdeCompra()+" unidades",
                LocalDateTime.now(),
                compra.getComprador(),
                compra.getProduto()
        );
    }

    private boolean ValidarCompra(Compra compra) {
        return compra != null && compra.validarEstoque();
    }
}
