package br.com.cartao.fatura.service;

import br.com.cartao.fatura.consumer.BuscaCartaoConsumer;
import br.com.cartao.fatura.domain.integration.CartaoResponseIntegracao;
import br.com.cartao.fatura.utils.OfuscaDadosSensiveis;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 6
 */

@Service
public class BuscaCartaoIntegracaoService {

    private static Logger logger = LoggerFactory.getLogger(BuscaCartaoIntegracaoService.class);
    // +1
    private final BuscaCartaoConsumer buscaCartaoConsumer;

    public BuscaCartaoIntegracaoService(BuscaCartaoConsumer buscaCartaoConsumer) {
        this.buscaCartaoConsumer = buscaCartaoConsumer;
    }
    // +1
    public Optional<CartaoResponseIntegracao> busca(String idCartao){
        // +1
        logger.info("Iniciando busca pelas configurações do cartão pelo id: {}", OfuscaDadosSensiveis.executa(idCartao));
        // +1
        try{
            // +1
            CartaoResponseIntegracao cartaoResponseIntegracao = buscaCartaoConsumer.buscaCartao(idCartao);
            logger.info("Busca de cartão realizada com Sucesso.");
            return Optional.ofNullable(cartaoResponseIntegracao);
        }
        // +1
        catch (FeignException exception){
            logger.warn("Erro na busca do cartão: {}", exception.getMessage());
            return Optional.empty();
        }

    }

}
