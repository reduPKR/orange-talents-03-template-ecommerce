package com.mercado.livre.compra;

import com.mercado.livre.exception.ErroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compra/produto")
public class CompraController {
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CompraRequest compraRequest, BindingResult result){
        if(!result.hasErrors()){
            return ResponseEntity.ok().build();
        }

        List<ErroResponse> erros = result.getFieldErrors()
                .stream()
                .map(ErroResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }
}
