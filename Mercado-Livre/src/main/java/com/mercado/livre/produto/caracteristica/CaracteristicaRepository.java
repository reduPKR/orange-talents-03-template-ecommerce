package com.mercado.livre.produto.caracteristica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
    Optional<Caracteristica> findByNomeAndDescricao(String nome, String descricao);
}
