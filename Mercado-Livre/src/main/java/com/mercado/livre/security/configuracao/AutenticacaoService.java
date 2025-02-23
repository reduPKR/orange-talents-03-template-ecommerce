package com.mercado.livre.security.configuracao;

import com.mercado.livre.usuario.Usuario;
import com.mercado.livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String loginUsuario) throws UsernameNotFoundException {
        Optional<Usuario> optional = usuarioRepository.findByLogin(loginUsuario);
        if(optional.isPresent()) {
            return optional.get();
        }

        throw new UsernameNotFoundException("Login de usuario não localizado");
    }
}
