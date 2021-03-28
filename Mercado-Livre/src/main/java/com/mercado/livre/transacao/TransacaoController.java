package com.mercado.livre.transacao;

import com.mercado.livre.compra.Compra;
import com.mercado.livre.compra.CompraRepository;
import com.mercado.livre.email.EmailFake;
import com.mercado.livre.exception.ErroResponse;
import com.mercado.livre.notaFiscal.NotaFiscalFake;
import com.mercado.livre.perguntas.PerguntaProduto;
import com.mercado.livre.ranking.RankingFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class TransacaoController {
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EmailFake emailFake;
    @Autowired
    private RankingFake rankingFake;
    @Autowired
    private NotaFiscalFake notaFiscalFake;

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> cadastroPaypal(@PathVariable long id,
                                            @RequestBody @Valid PaypalRequest paypalRequest,
                                            BindingResult result){
        if(!result.hasErrors()){
            Transacao transacao = paypalRequest.converter(id, compraRepository);

            if(transacao!=null){
                transacaoRepository.save(transacao);

                if(transacao.getStatus().equals("1")){
                    finalizarCompra(transacao);
                    return ResponseEntity.ok().build();
                }else{
                    finalizarCompraErro(transacao);
                }
            }
            result.addError(new FieldError("Transação", "Erro transacional", "Não foi possivel completar a transação"));
        }

        List<ErroResponse> listarErros = result.getFieldErrors()
                .stream()
                .map(ErroResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(listarErros);
    }

    private void finalizarCompraErro(Transacao transacao) {
        //é ao contrario vai do vendedor para o cliente
        emailFake.send(
                transacao.getCompra().getComprador().getUsername(),
                transacao.getCompra().getProduto().getDono().getUsername(),
                GerarEmail("Erro ao realizar compra", transacao.getCompra())
        );
    }

    private void finalizarCompra(Transacao transacao) {
        notaFiscalFake.gerarNota(transacao.getCompra().getId(), transacao.getCompra().getComprador().getId());
        rankingFake.adicionarVenda(transacao.getCompra().getId(), transacao.getCompra().getProduto().getDono().getId());
        emailFake.send(
                transacao.getCompra().getProduto().getDono().getUsername(),
                transacao.getCompra().getComprador().getUsername(),
                GerarEmail("Compra finalizada com sucesso", transacao.getCompra())
        );
    }

    private PerguntaProduto GerarEmail(String titulo, Compra compra) {
        return new PerguntaProduto(
                titulo,
                LocalDateTime.now(),
                compra.getComprador(),
                compra.getProduto()
        );
    }
}
