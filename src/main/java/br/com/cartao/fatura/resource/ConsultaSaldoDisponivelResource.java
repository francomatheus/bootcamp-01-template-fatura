package br.com.cartao.fatura.resource;

import br.com.cartao.fatura.domain.integration.CartaoResponseIntegracao;
import br.com.cartao.fatura.domain.model.Cartao;
import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.response.LimiteDisponivelResponseDto;
import br.com.cartao.fatura.repository.CartaoRepository;
import br.com.cartao.fatura.repository.FaturaRepository;
import br.com.cartao.fatura.service.BuscaCartaoIntegracaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;



@RestController
@RequestMapping("/v1/cartoes")
public class ConsultaSaldoDisponivelResource {

    private static Logger logger = LoggerFactory.getLogger(ConsultaSaldoDisponivelResource.class);
    // +1
    private final CartaoRepository cartaoRepository;
    // +1
    private final BuscaCartaoIntegracaoService buscaCartaoIntegracaoService;
    // +1
    private final FaturaRepository faturaRepository;

    public ConsultaSaldoDisponivelResource(CartaoRepository cartaoRepository, BuscaCartaoIntegracaoService buscaCartaoIntegracaoService, FaturaRepository faturaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.buscaCartaoIntegracaoService = buscaCartaoIntegracaoService;
        this.faturaRepository = faturaRepository;
    }

    @GetMapping("/{id}/limites-disponiveis")
    public ResponseEntity<?> consultaLimiteDisponivel(@PathVariable(value = "id",required = true)String idCartao){
        logger.info("Requisição para consultar saldo disponivel recebdio para o cartaoId: {}", idCartao);

        // +1
        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);
        // +1
        if(cartaoBuscado.isEmpty()){
            logger.info("Cartão solicitado não encontrado, id: {}", idCartao);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // +1
        Optional<CartaoResponseIntegracao> cartaoBuscadoSitemaLegado = buscaCartaoIntegracaoService.busca(cartaoBuscado.get().getNumeroCartao());
        // +1
        if(cartaoBuscadoSitemaLegado.isEmpty()){
            logger.info("Cartão/Limite solicitado não encontrado, id: {}", idCartao);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Integer limite = cartaoBuscadoSitemaLegado.get().getLimite();
        // +1
        Optional<Fatura> faturaCartaoSolicitado = faturaRepository.findByCartaoIdCartao(idCartao);
        // +1
        LimiteDisponivelResponseDto limiteDisponivelResponseDto = new LimiteDisponivelResponseDto(cartaoBuscadoSitemaLegado.get(), faturaCartaoSolicitado);;

        logger.info("Limite disponivel calculado com sucesso: {}", limiteDisponivelResponseDto.getLimiteDisponivel());
        return ResponseEntity.ok().body(limiteDisponivelResponseDto);
    }
}
