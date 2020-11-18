package br.com.cartao.fatura.consumer;

import br.com.cartao.fatura.domain.integration.CartaoResponseIntegracao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "buscaCartao", url = "${url.feign-cartao}")
public interface BuscaCartaoConsumer {

    @RequestMapping(method = RequestMethod.GET, path = "/api/cartoes/{id}")
    public CartaoResponseIntegracao buscaCartao(@PathVariable(value = "id",required = true) String numeroCartao);
}
