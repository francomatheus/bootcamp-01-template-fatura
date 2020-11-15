package br.com.cartao.fatura.listeners;

import br.com.cartao.fatura.domain.listener.TransacaoCartaoListener;
import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.Gastos;
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

    public TransacaoListener( FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}", groupId = "")
    // +1
    public void listenerTransacao(TransacaoCartaoListener transacaoCartaoListener){
        logger.info("Evento recebido com sucesso, id: {}", transacaoCartaoListener.getId());
        // +1
        Fatura fatura;

        Optional<Fatura> faturaBuscadaPorCartao = faturaRepository.findByIdCartao(transacaoCartaoListener.getIdCartao());
        // +1
        Gastos gastos = transacaoCartaoListener.toGastos();
        // +1
        if (faturaBuscadaPorCartao.isEmpty()){
            logger.info("Não existe fatura para o cartão. Criando uma nova fatura para o cartão.");
            fatura = new Fatura(transacaoCartaoListener.getIdCartao());
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
