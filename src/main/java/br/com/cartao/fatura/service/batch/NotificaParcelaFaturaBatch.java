package br.com.cartao.fatura.service.batch;

import br.com.cartao.fatura.domain.enums.StatusParcelaFaturaIntegracaoResponse;
import br.com.cartao.fatura.domain.enums.StatusParcelarFatura;
import br.com.cartao.fatura.domain.model.ParcelaFatura;
import br.com.cartao.fatura.domain.request.ParcelaFaturaIntegracaoRequest;
import br.com.cartao.fatura.domain.response.ParcelarFaturaIntegracaoResponse;
import br.com.cartao.fatura.repository.ParcelaFaturaRepository;
import br.com.cartao.fatura.service.NotificaParcelamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotificaParcelaFaturaBatch {

    private static Logger logger = LoggerFactory.getLogger(NotificaParcelaFaturaBatch.class);

    private final ParcelaFaturaRepository parcelaFaturaRepository;
    private final NotificaParcelamentoService notificaParcelamentoService;

    public NotificaParcelaFaturaBatch(ParcelaFaturaRepository parcelaFaturaRepository, NotificaParcelamentoService notificaParcelamentoService) {
        this.parcelaFaturaRepository = parcelaFaturaRepository;
        this.notificaParcelamentoService = notificaParcelamentoService;
    }

    @Scheduled(fixedDelayString = "${scheduled.time-notifica-parcela}")
    public void avisaSistemaLegado(){
        logger.info("Inicio do serviço de notificação de parcelamento de fatura.");

        List<ParcelaFatura> parcelaFaturasNaoNotificadas =  buscaTodasParcelaFaturaNaoNotificadas();
        logger.info("Tamanho da lista de pedidos de parcelamento de fatura: {}", parcelaFaturasNaoNotificadas.size());
        percorreListaDeParcelaFaturaNaoNotificadas(parcelaFaturasNaoNotificadas);
    }

    private List<ParcelaFatura> buscaTodasParcelaFaturaNaoNotificadas() {
        logger.info("Buscando todos os pedidos de parcelamento de fatura corrente");
        return parcelaFaturaRepository.findAllByAvisoLegadoIsFalse();
    }

    private void percorreListaDeParcelaFaturaNaoNotificadas(List<ParcelaFatura> parcelaFaturasNaoNotificadas) {
        logger.info("Percorrer todos os elementos da lista de pedidos de parcelamento de fatura.");
        parcelaFaturasNaoNotificadas.stream()
                .forEach(parcelaFatura -> {
                    logger.info("Notificando sistema bancario sobre parcelamento de fatura para a seguinte fatura: {} ", parcelaFatura.getFatura().getId());
                    ParcelaFaturaIntegracaoRequest parcelaFaturaIntegracaoRequest = new ParcelaFaturaIntegracaoRequest(parcelaFatura);
                    Optional<ParcelarFaturaIntegracaoResponse> notifica = notificaParcelamentoService.notifica(parcelaFatura.numeroCartao(), parcelaFaturaIntegracaoRequest);
                    salvaResultadoNotificacaoParcelaFatura(parcelaFatura, notifica);
                });
    }

    public void salvaResultadoNotificacaoParcelaFatura(ParcelaFatura parcelaFatura, Optional<ParcelarFaturaIntegracaoResponse> notifica){
        logger.info("Inicio de armazenar resultado obtido da notificação do pedido de parcelamento");
        if (notifica.get().getResultado().equals(StatusParcelaFaturaIntegracaoResponse.APROVADO)){
            logger.info("Parcelamento de fatura aprovado, idFatura: {}", parcelaFatura.getFatura().getId());
            parcelaFatura.mudarStatusParcelaFatura(StatusParcelarFatura.APROVADO);
            parcelaFatura.avisoSistemaLegadoComSucesso();
        }

        else if (notifica.get().getResultado().equals(StatusParcelaFaturaIntegracaoResponse.NEGADO)){
            logger.info("Parcelamento de fatura negado, idFatura: {}", parcelaFatura.getFatura().getId());
            parcelaFatura.mudarStatusParcelaFatura(StatusParcelarFatura.NEGADO);
            parcelaFatura.avisoSistemaLegadoComSucesso();
        }

        parcelaFaturaRepository.save(parcelaFatura);
        logger.info("Status do pedido de parcelamento de fatura armazenado: {}", parcelaFatura.getStatusParcelarFatura());
    }

}
