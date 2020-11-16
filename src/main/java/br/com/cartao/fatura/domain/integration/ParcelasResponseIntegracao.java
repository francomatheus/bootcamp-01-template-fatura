package br.com.cartao.fatura.domain.integration;

import java.math.BigDecimal;

public class ParcelasResponseIntegracao {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    @Deprecated
    public ParcelasResponseIntegracao() {
    }

    public ParcelasResponseIntegracao(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
