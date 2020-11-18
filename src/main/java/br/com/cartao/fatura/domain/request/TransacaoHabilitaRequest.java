package br.com.cartao.fatura.domain.request;

import br.com.cartao.fatura.domain.model.Cartao;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class TransacaoHabilitaRequest {

    @NotBlank
    private final String idCartao;
    @NotBlank
    private final String email;

    public TransacaoHabilitaRequest(@NotBlank String idCartao,@NotBlank String email) {
        this.idCartao = idCartao;
        this.email = email;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }

    public TransacaoHabilitaIntegracaoRequest toIntegracao(EntityManager manager){
        Cartao cartao = manager.find(Cartao.class, this.idCartao);
        Assert.notNull(cartao, "Cartão não encontrado para o idCartao solicitado");

        return new TransacaoHabilitaIntegracaoRequest(cartao.getNumeroCartao(), this.email);
    }
}
