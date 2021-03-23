package com.mercado.livre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;

public class UsuarioRequest {
    @NotNull
    @NotBlank
    @Email
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
