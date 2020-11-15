package br.com.cartao.fatura.domain.response;

public class TransacaoHabilitaIntegracaoResponse {
    private final String id;
    private final String email;

    public TransacaoHabilitaIntegracaoResponse(String idCartao, String email) {
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
