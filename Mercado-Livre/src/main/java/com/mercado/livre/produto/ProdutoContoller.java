package com.mercado.livre.produto;

import com.mercado.livre.categoria.CategoriaRepository;
import com.mercado.livre.produto.caracteristica.Caracteristica;
import com.mercado.livre.produto.caracteristica.CaracteristicaRepository;
import com.mercado.livre.usuario.Usuario;
import com.mercado.livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produtos")
public class ProdutoContoller {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, BindingResult result){
        if(!result.hasErrors()){
            Produto produto = produtoRequest.converter(categoriaRepository, usuarioRepository);

            produto.salvarCaracteristicas(caracteristicaRepository);
            if(produto.validarCaracteristica(caracteristicaRepository)){
                produtoRepository.save(produto);

                if(produto.getId() != 0){
                    return ResponseEntity.ok().build();
                }else{
                    result.addError(new FieldError("Produtos", "Produto", "Ocorreu algum erro ao adicionar o produto."));
                }
            }else{
                result.addError(new FieldError("Produtos", "caracteristicas", "Ocorreu algum erro ao adicionar as caracteristicas."));
            }
        }

        List<ProdutoErroResponse> erros = result.getFieldErrors()
                .stream()
                .map(ProdutoErroResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(erros);

    }
}
