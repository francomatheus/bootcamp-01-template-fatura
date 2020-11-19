package br.com.cartao.fatura.domain.request;

import br.com.cartao.fatura.domain.model.*;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class RenegociacaoFaturaRequest {

    @NotNull
    @Positive
    private final Integer quantidade;
    @NotNull
    @Positive
    private final BigDecimal valor;

    public RenegociacaoFaturaRequest(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public RenegociacaoFatura toModel(Cartao cartao, Fatura fatura) {
        List<Gastos> gastos = fatura.retornaGastosCorrentesAtuaisOrdenados();
        FaturaCorrente faturaCorrente = new FaturaCorrente(cartao, gastos);

        verificaValorFaturaValorDaRenegociacao(fatura);

        return new RenegociacaoFatura(this.quantidade, this.valor, cartao, fatura, faturaCorrente);
    }

    private void verificaValorFaturaValorDaRenegociacao(Fatura fatura){
        BigDecimal valorTotalFaturaCorrente = fatura.retornaValorTotalFaturaCorrente().setScale(2);

        BigDecimal valorSolicitadoRenegociacao = this.valor.multiply(new BigDecimal(this.quantidade)).setScale(2);

        Assert.isTrue(valorSolicitadoRenegociacao.equals(valorTotalFaturaCorrente), "Valor informado na negociação não da o valor da fatura corrente atual.");

    }
}
