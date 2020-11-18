package br.com.cartao.fatura.listeners;

import br.com.cartao.fatura.domain.listener.TransacaoCartaoListener;
import br.com.cartao.fatura.domain.model.Cartao;
import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.Gastos;
import br.com.cartao.fatura.repository.CartaoRepository;
import br.com.cartao.fatura.repository.FaturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransacaoListener {

    private static Logger logger = LoggerFactory.getLogger(TransacaoListener.class);

    // +1
    private final FaturaRepository faturaRepository;

    private final CartaoRepository cartaoRepository;

    public TransacaoListener(FaturaRepository faturaRepository, CartaoRepository cartaoRepository) {
        this.faturaRepository = faturaRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}", containerFactory = "kafkaListenerContainerFactory")
    // +1
    public void listenerTransacao(TransacaoCartaoListener transacaoCartaoListener){
        logger.info("Evento recebido com sucesso, id: {}", transacaoCartaoListener.getId());
        // +1
        Fatura fatura;

        Optional<Fatura> faturaBuscadaPorCartao = faturaRepository.findByCartaoNumeroCartao(transacaoCartaoListener.getIdCartao());
        // +1
        Gastos gastos = transacaoCartaoListener.toGastos();
        Optional<Cartao> cartaoBuscado = cartaoRepository.findByNumeroCartao(transacaoCartaoListener.getIdCartao());
        // +1
        if (faturaBuscadaPorCartao.isEmpty()){
            logger.info("Não existe fatura para o cartão. Criando uma nova fatura para o cartão.");
            fatura = new Fatura(cartaoBuscado.get());
        }
        // +1
        else{
            logger.info("Associando fatura para adicionar novos gastos. ");
            fatura = faturaBuscadaPorCartao.get();
        }

        fatura.adicionaGasto(gastos);
        Fatura faturaSalva = faturaRepository.save(fatura);

        logger.info("Fatura armazenada com sucesso, idFatura: {}", faturaSalva.getId());

    }
}
