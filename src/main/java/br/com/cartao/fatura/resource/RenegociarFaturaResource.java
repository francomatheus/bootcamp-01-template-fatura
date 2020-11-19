package br.com.cartao.fatura.resource;

import br.com.cartao.fatura.domain.model.Cartao;
import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.RenegociacaoFatura;
import br.com.cartao.fatura.domain.request.RenegociacaoFaturaRequest;
import br.com.cartao.fatura.domain.response.RenegociacaoFaturaResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/v1/cartoes")
public class RenegociarFaturaResource {

    private static Logger logger = LoggerFactory.getLogger(RenegociarFaturaResource.class);

    @PersistenceContext
    private final EntityManager manager;

    public RenegociarFaturaResource(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping("/{idCartao}/faturas/{idFatura}/renegociacao")
    @Transactional
    public ResponseEntity<?> renogociaFatura(@PathVariable(value = "idCartao", required = true) String idCartao,
                                             @PathVariable(value = "idFatura", required = true) String idFatura,
                                             @RequestBody RenegociacaoFaturaRequest renegociacaoFaturaRequest,
                                             UriComponentsBuilder uriComponentsBuilder){
        logger.info("Requisição para renegociação da fatura com id: {} recebida, para o seguinte idCartao: {}", idFatura, idCartao);

        Cartao cartao = manager.find(Cartao.class, idCartao);
        Fatura fatura = manager.find(Fatura.class, idFatura);

        if (cartao == null || fatura == null){
            return ResponseEntity.notFound().build();
        }

        RenegociacaoFatura renegociacaoFatura = renegociacaoFaturaRequest.toModel(cartao, fatura);

        manager.persist(renegociacaoFatura);

        RenegociacaoFaturaResponseDto renegociacaoFaturaResponseDto = new RenegociacaoFaturaResponseDto(renegociacaoFatura);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/cartoes/{idCartao}/faturas/{idFatura}/renegociacao/{id}").buildAndExpand(idCartao, idFatura, renegociacaoFaturaResponseDto.getId()).toUri())
                .build();
    }
}
