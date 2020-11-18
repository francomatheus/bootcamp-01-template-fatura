package br.com.cartao.fatura.resource;

import br.com.cartao.fatura.domain.integration.CartaoResponseIntegracao;
import br.com.cartao.fatura.domain.model.Cartao;
import br.com.cartao.fatura.domain.model.ParcelaFatura;
import br.com.cartao.fatura.domain.request.ParcelarFaturaRequest;
import br.com.cartao.fatura.domain.response.ParcelarFaturaResponseDto;
import br.com.cartao.fatura.repository.CartaoRepository;
import br.com.cartao.fatura.repository.ParcelaFaturaRepository;
import br.com.cartao.fatura.service.BuscaCartaoIntegracaoService;
import br.com.cartao.fatura.utils.OfuscaDadosSensiveis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe -
 */

@RestController
@RequestMapping("/v1/cartoes/")
public class ParcelarFaturaResource {

    private static Logger logger = LoggerFactory.getLogger(ParcelarFaturaResource.class);

    // +1
    private final CartaoRepository cartaoRepository;
    private final EntityManager manager;

    public ParcelarFaturaResource(CartaoRepository cartaoRepository, EntityManager manager) {
        this.cartaoRepository = cartaoRepository;
        this.manager = manager;
    }

    @PostMapping("/{idCartao}/faturas/{idFatura}/parcelar")
    @Transactional
    // +1
    public ResponseEntity<?> parcelaFaturaAtual(@PathVariable(value = "idCartao",required = true) String idCartao,
                                                @PathVariable(value = "idFatura",required = true) String idFatura,
                                                @RequestBody @Valid ParcelarFaturaRequest parcelarFaturaRequest,
                                                UriComponentsBuilder uriComponentsBuilder){

        logger.info("Requisição para parcelar fatura: {}, do cartão: {} recebida", idFatura,idCartao);
        // +1
        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);
        // +1
        if (cartaoBuscado.isEmpty()){
            logger.info("Cartão não existe, id: {}", idCartao);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        // +1
        ParcelaFatura parcelaFatura = parcelarFaturaRequest.toModel(idFatura, manager);

        manager.persist(parcelaFatura);
        // +1
        ParcelarFaturaResponseDto parcelarFaturaResponseDto = new ParcelarFaturaResponseDto(parcelaFatura);

        logger.info("Parcelar fatura aceita com sucesso, id: {}", idCartao);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/cartoes/{idCartao}/faturas/{idFatura}/parcelar/{id}").buildAndExpand(idCartao, idFatura, parcelarFaturaResponseDto.getId()).toUri())
                .build();
    }
}
