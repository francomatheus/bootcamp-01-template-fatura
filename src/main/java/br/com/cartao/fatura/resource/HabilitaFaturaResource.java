package br.com.cartao.fatura.resource;

import br.com.cartao.fatura.domain.request.TransacaoHabilitaIntegracaoRequest;
import br.com.cartao.fatura.domain.request.TransacaoHabilitaRequest;
import br.com.cartao.fatura.service.HabilitaTransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/faturas")
public class HabilitaFaturaResource {

    private static Logger logger = LoggerFactory.getLogger(HabilitaFaturaResource.class);

    @Autowired
    private final HabilitaTransacaoService habilitaTransacaoService;

    private final EntityManager manager;

    public HabilitaFaturaResource(HabilitaTransacaoService habilitaTransacaoService, EntityManager manager) {
        this.habilitaTransacaoService = habilitaTransacaoService;
        this.manager = manager;
    }

    @PostMapping("/habilita")
    public ResponseEntity<?> habilita(@RequestBody @Valid TransacaoHabilitaRequest transacaoHabilitaRequest){
        logger.info("Requisição para habilitar transações recebidas, email: {}", transacaoHabilitaRequest.getEmail());

        TransacaoHabilitaIntegracaoRequest transacaoHabilitaIntegracaoRequest = transacaoHabilitaRequest.toIntegracao(manager);

        habilitaTransacaoService.habilitaCartao(transacaoHabilitaIntegracaoRequest);

        logger.info("Fatura habilitada com sucesso!");
        return ResponseEntity.ok().build();
    }
}
