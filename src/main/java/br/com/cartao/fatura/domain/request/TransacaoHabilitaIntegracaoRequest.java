package br.com.cartao.fatura.domain.request;

public class TransacaoHabilitaIntegracaoRequest {
    private final String id;
    private final String email;

    public TransacaoHabilitaIntegracaoRequest(String idCartao, String email) {
        this.id = idCartao;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
