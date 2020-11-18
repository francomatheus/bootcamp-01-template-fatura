package br.com.cartao.fatura.consumer;

import br.com.cartao.fatura.domain.request.ParcelaFaturaIntegracaoRequest;
import br.com.cartao.fatura.domain.response.ParcelarFaturaIntegracaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "parcelarFatura", url = "${url.feign-cartao}")
public interface NotificaParcelarFaturaConsumer {

    @RequestMapping(method = RequestMethod.POST, path = "/api/cartoes/{id}/parcelas")
    public ParcelarFaturaIntegracaoResponse notifica(@PathVariable("id") String numeroCartao,
                                                     @RequestBody @Valid ParcelaFaturaIntegracaoRequest parcelaFaturaIntegracaoRequest);
}
