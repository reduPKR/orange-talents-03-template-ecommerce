package com.mercado.livre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.converter();
        usuarioRepository.save(usuario);
        if(usuario.getId() != 0)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
