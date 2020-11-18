package br.com.cartao.fatura.domain.request;

import br.com.cartao.fatura.domain.model.ParcelaFatura;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ParcelaFaturaIntegracaoRequest {

    @NotBlank
    private String identificadorDaFatura;
    @NotNull
    private Integer quantidade;
    @NotNull
    private BigDecimal valor;

    @Deprecated
    public ParcelaFaturaIntegracaoRequest() {
    }

    public ParcelaFaturaIntegracaoRequest(@NotBlank String identificadorDaFatura, @NotNull Integer quantidade, @NotNull BigDecimal valor) {
        this.identificadorDaFatura = identificadorDaFatura;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ParcelaFaturaIntegracaoRequest(ParcelaFatura parcelaFatura) {
        this.identificadorDaFatura = parcelaFatura.getFatura().getId();
        this.quantidade = parcelaFatura.getQuantidade();
        this.valor = parcelaFatura.getValor();
    }

    public String getIdentificadorDaFatura() {
        return identificadorDaFatura;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
