package br.com.cartao.fatura.listeners;

import br.com.cartao.fatura.domain.listener.CartaoPropostaListener;
import br.com.cartao.fatura.domain.model.Cartao;
import br.com.cartao.fatura.repository.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CartaoListener {

    private static Logger logger = LoggerFactory.getLogger(CartaoListener.class);

    @Value("${spring.kafka.topic.cartao}")
    private String topicName;

    private final CartaoRepository cartaoRepository;

    public CartaoListener(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.cartao}",containerFactory = "kafkaListenerContainerFactoryCartao")
    public void listenerCartao(CartaoPropostaListener cartaoPropostaListener ){

        logger.info("Evento recebido pelo topic: {}", topicName);
        logger.info("Evento recebido com idProposta: {}, e idCartao: {}", cartaoPropostaListener.getIdProposta(), cartaoPropostaListener.getIdCartao());
        System.out.println(cartaoPropostaListener);

        Cartao cartao = cartaoPropostaListener.toModel();

        cartaoRepository.save(cartao);

    }
}
