package br.com.cartao.fatura.consumer;

import br.com.cartao.fatura.domain.request.TransacaoHabilitaIntegracaoRequest;
import br.com.cartao.fatura.domain.response.TransacaoHabilitaIntegracaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "habilitaTransacao", url = "${url.feign-transacao}")
public interface TransacaoHabilitaConsumer {

    @RequestMapping(method = RequestMethod.POST, path = "/api/cartoes")
    public TransacaoHabilitaIntegracaoResponse habilita(@RequestBody TransacaoHabilitaIntegracaoRequest transacaoHabilitaIntegracaoRequest);

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/cartoes/{id}")
    public void desabilita(@PathVariable(value = "id",required = true)String idCartao);
}
