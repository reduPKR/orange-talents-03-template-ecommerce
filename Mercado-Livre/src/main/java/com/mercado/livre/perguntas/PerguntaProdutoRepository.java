package com.mercado.livre.perguntas;

import com.mercado.livre.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerguntaProdutoRepository extends JpaRepository<PerguntaProduto, Long> {
    Optional<List<PerguntaProduto>> findByProduto(Produto produto);
}
