package br.com.cartao.fatura.resource;

import br.com.cartao.fatura.domain.integration.CartaoResponseIntegracao;
import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.response.LimiteDisponivelResponseDto;
import br.com.cartao.fatura.repository.FaturaRepository;
import br.com.cartao.fatura.service.BuscaCartaoIntegracaoService;
import br.com.cartao.fatura.utils.OfuscaDadosSensiveis;
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
    private final BuscaCartaoIntegracaoService buscaCartaoIntegracaoService;
    // +1
    private final FaturaRepository faturaRepository;

    public ConsultaSaldoDisponivelResource(BuscaCartaoIntegracaoService buscaCartaoIntegracaoService, FaturaRepository faturaRepository) {
        this.buscaCartaoIntegracaoService = buscaCartaoIntegracaoService;
        this.faturaRepository = faturaRepository;
    }

    @GetMapping("/limites-disponiveis/{id}")
    public ResponseEntity<?> consultaLimiteDisponivel(@PathVariable(value = "id",required = true)String idCartao){
        // +1
        logger.info("Requisição para consultar saldo disponivel recebdio para o cartaoId: {}", OfuscaDadosSensiveis.executa(idCartao));
        // +1
        Optional<CartaoResponseIntegracao> buscaCartao = buscaCartaoIntegracaoService.busca(idCartao);
        // +1
        if(buscaCartao.isEmpty()){
            logger.info("Cartão solicidade não encontrado, id: {}", OfuscaDadosSensiveis.executa(idCartao));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Integer limite = buscaCartao.get().getLimite();
        // +1
        Optional<Fatura> faturaCartaoSolicitado = faturaRepository.findByIdCartao(idCartao);
        // +1
        LimiteDisponivelResponseDto limiteDisponivelResponseDto = new LimiteDisponivelResponseDto(buscaCartao.get(), faturaCartaoSolicitado);;

        logger.info("Limite disponivel calculado com sucesso: {}", limiteDisponivelResponseDto.getLimiteDisponivel());
        return ResponseEntity.ok().body(limiteDisponivelResponseDto);
    }
}
