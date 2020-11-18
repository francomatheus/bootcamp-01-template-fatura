package br.com.cartao.fatura.service;

import br.com.cartao.fatura.consumer.NotificaParcelarFaturaConsumer;
import br.com.cartao.fatura.domain.request.ParcelaFaturaIntegracaoRequest;
import br.com.cartao.fatura.domain.response.ParcelarFaturaIntegracaoResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 5
 */

@Service
public class NotificaParcelamentoService {

    private static Logger logger = LoggerFactory.getLogger(NotificaParcelamentoService.class);
    // +1
    private final NotificaParcelarFaturaConsumer notificaParcelarFaturaConsumer;

    public NotificaParcelamentoService(NotificaParcelarFaturaConsumer notificaParcelarFaturaConsumer) {
        this.notificaParcelarFaturaConsumer = notificaParcelarFaturaConsumer;
    }

    // +1
    public Optional<ParcelarFaturaIntegracaoResponse> notifica(String idCartao, ParcelaFaturaIntegracaoRequest parcelaFaturaIntegracaoRequest){

        logger.info("Inicio da notificação do sistema bacario sobre parcelamento da fatura corrente");

        // +1
        try{
            // +1
            ParcelarFaturaIntegracaoResponse notifica = notificaParcelarFaturaConsumer.notifica(idCartao, parcelaFaturaIntegracaoRequest);
            logger.info("Notificação realizada com sucesso. ");
            return Optional.ofNullable(notifica);
        }
        // +1
        catch (FeignException exception){
            logger.info("Erro ao notificar sistema bancario sobre parcelamento de fatura corrente");
            logger.warn("Erro: {}", exception.getMessage());
            return Optional.empty();
        }
    }
}
