package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.enums.StatusParcelaFaturaIntegracaoResponse;

public class ParcelarFaturaIntegracaoResponse {

    private StatusParcelaFaturaIntegracaoResponse resultado;

    public ParcelarFaturaIntegracaoResponse() {
    }

    public ParcelarFaturaIntegracaoResponse(StatusParcelaFaturaIntegracaoResponse resultado) {
        this.resultado = resultado;
    }

    public StatusParcelaFaturaIntegracaoResponse getResultado() {
        return resultado;
    }

    public ParcelarFaturaIntegracaoResponse setResultado(StatusParcelaFaturaIntegracaoResponse resultado) {
        this.resultado = resultado;
        return this;
    }
}
