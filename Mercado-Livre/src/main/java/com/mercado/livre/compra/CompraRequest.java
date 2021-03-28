package com.mercado.livre.compra;

import com.mercado.livre.produto.Produto;
import com.mercado.livre.produto.ProdutoRepository;
import com.mercado.livre.usuario.Usuario;
import com.mercado.livre.usuario.UsuarioRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CompraRequest {
    @Min(1)
    private int qtdeCompra;
    @NotNull
    private Gateway gateway;
    @NotNull
    private long idComprador;
    @NotNull
    private long idProduto;

    public int getQtdeCompra() {
        return qtdeCompra;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public long getIdComprador() {
        return idComprador;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public Compra converter(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        Optional<Usuario> usuario = usuarioRepository.findById(idComprador);
        Optional<Produto> produto = produtoRepository.findById(idProduto);
        if(usuario.isPresent() && produto.isPresent())
            return new Compra(
                    this.qtdeCompra,
                    this.gateway,
                    usuario.get(),
                    produto.get()
            );
        return null;
    }
}
