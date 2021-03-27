package com.mercado.livre.opiniao;

import com.mercado.livre.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpiniaoProdutoRepository extends JpaRepository<OpiniaoProduto, Long> {
    Optional<List<OpiniaoProduto>> findByProduto(Produto produto);
}
