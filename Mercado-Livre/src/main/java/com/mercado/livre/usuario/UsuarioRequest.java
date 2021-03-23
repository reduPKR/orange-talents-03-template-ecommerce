package com.mercado.livre.usuario;

import com.mercado.livre.validador.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;

public class UsuarioRequest {
    @NotNull
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotNull
    @NotBlank
    @Min(6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        String novaSenha = gerarHash();
        return new Usuario(login, novaSenha);
    }

    private String gerarHash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
