package br.com.cartao.fatura.domain.request;

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

    public TransacaoHabilitaIntegracaoRequest toIntegracao(){
        return new TransacaoHabilitaIntegracaoRequest(this.idCartao, this.email);
    }
}
