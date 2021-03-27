package com.mercado.livre.produto;

import com.mercado.livre.categoria.CategoriaRepository;
import com.mercado.livre.exception.ErroResponse;
import com.mercado.livre.opiniao.OpiniaoProduto;
import com.mercado.livre.opiniao.OpiniaoProdutoRepository;
import com.mercado.livre.perguntas.PerguntaProduto;
import com.mercado.livre.perguntas.PerguntaProdutoRepository;
import com.mercado.livre.produto.imagens.ImagemProduto;
import com.mercado.livre.produto.imagens.ImagensRequest;
import com.mercado.livre.produto.imagens.UploaderFake;
import com.mercado.livre.usuario.Usuario;
import com.mercado.livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produtos")
public class ProdutoContoller {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @Autowired
    private OpiniaoProdutoRepository opiniaoProdutoRepository;

    @Autowired
    private PerguntaProdutoRepository perguntaProdutoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, BindingResult result){
        if(!result.hasErrors()){
            Produto produto = produtoRequest.converter(categoriaRepository, usuarioRepository);

            produtoRepository.save(produto);
            if(produto.getId() != 0){
                return ResponseEntity.ok().build();
            }else{
                result.addError(new FieldError("Produtos", "Produto", "Ocorreu algum erro ao adicionar o produto."));
            }
        }

        return ResponseEntity.badRequest().body(listarErros(result));
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> cadastrarImegem(@Valid ImagensRequest imagens,
                                             @PathVariable("id") long id,
                                             @AuthenticationPrincipal Usuario usuario,
                                             BindingResult result){
        if(!result.hasErrors()){
            Produto produto = produtoRepository.findById(id).get();

            if(produto.getDono().getId().equals(usuario.getId())){
                /*Vai simular o upload de imagem para um sistema externo*/
                Set<String> links = uploaderFake.send(imagens.getImagens());
                produto.associarImagens(links);
                produtoRepository.save(produto);

                return ResponseEntity.ok().build();
            }else{
                result.addError(new FieldError("Produtos", "cadastro de imagem", "Usuario não é o dono do produto."));
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(listarErros(result));
            }
        }

        return ResponseEntity.badRequest().body(listarErros(result));
    }

    private List<ErroResponse> listarErros(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(ErroResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhes(@PathVariable long id){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if(optionalProduto.isPresent()){
            Optional<List<OpiniaoProduto>> optionalOpiniao = opiniaoProdutoRepository.findByProduto(optionalProduto.get());
            Optional<List<PerguntaProduto>> optionalPergunta = perguntaProdutoRepository.findByProduto(optionalProduto.get());

            ProdutoDetalhadoResponse produto = new ProdutoDetalhadoResponse(optionalProduto.get(), optionalOpiniao, optionalPergunta);
            return ResponseEntity.ok().body(produto);
        }

        return ResponseEntity.badRequest().build();
    }
}
