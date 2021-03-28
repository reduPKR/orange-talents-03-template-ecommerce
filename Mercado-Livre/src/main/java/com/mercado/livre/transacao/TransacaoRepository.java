package com.mercado.livre.transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query("select 1 from Transacao where (status = 'SUCESSO' or status = '1') and transacao_gateway_id=:transacaoGatewayId")
    Optional<Transacao> findSucesso(long transacaoGatewayId);
}
