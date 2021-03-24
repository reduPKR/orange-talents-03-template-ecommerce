package com.mercado.livre.security;

import com.mercado.livre.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${mercado.jwt.expiration}")
    private String expiration;//Modifiquei

    @Value("${mercado.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Date agora = new Date();
        Date expira = new Date(agora.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API treino mercado livre")
                .setSubject(String.valueOf(usuario.getId()))
                .setIssuedAt(agora)
                .setExpiration(expira)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
