package br.com.cartao.fatura.service;

import br.com.cartao.fatura.consumer.TransacaoHabilitaConsumer;
import br.com.cartao.fatura.domain.request.TransacaoHabilitaIntegracaoRequest;
import br.com.cartao.fatura.domain.response.TransacaoHabilitaIntegracaoResponse;
import br.com.cartao.fatura.utils.OfuscaDadosSensiveis;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HabilitaTransacaoService {

    private static Logger logger = LoggerFactory.getLogger(HabilitaTransacaoService.class);

    private final TransacaoHabilitaConsumer transacaoHabilitaConsumer;

    public HabilitaTransacaoService(TransacaoHabilitaConsumer transacaoHabilitaConsumer) {
        this.transacaoHabilitaConsumer = transacaoHabilitaConsumer;
    }

    public Optional<TransacaoHabilitaIntegracaoResponse> habilitaCartao(TransacaoHabilitaIntegracaoRequest transacaoHabilitaIntegracaoRequest){
        logger.info("Inicio da Service que habilita as transações, para o usuario: {}.",transacaoHabilitaIntegracaoRequest.getEmail());
        try{
            TransacaoHabilitaIntegracaoResponse habilita = transacaoHabilitaConsumer.habilita(transacaoHabilitaIntegracaoRequest);
            logger.info("Habilitação realizada com sucesso para cartao: {}", OfuscaDadosSensiveis.executa(habilita.getId()));
            return Optional.ofNullable(habilita);
        }catch (FeignException exception){
            logger.warn("Erro ao habilitar o cartão : {}", OfuscaDadosSensiveis.executa(transacaoHabilitaIntegracaoRequest.getId()));
            logger.warn("Erro: {}", exception.getMessage());
            return Optional.empty();
        }
    }
}
