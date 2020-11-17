package br.com.cartao.fatura.domain.request;

import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.FaturaCorrente;
import br.com.cartao.fatura.domain.model.Gastos;
import br.com.cartao.fatura.domain.model.ParcelaFatura;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class ParcelarFaturaRequest {

    @NotNull @Positive
    private final Integer quantidade;
    @NotNull @Positive
    private final BigDecimal valor;

    public ParcelarFaturaRequest(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
    // +1
    public ParcelaFatura toModel(String idFatura, EntityManager manager){
        // +1
        Fatura fatura = manager.find(Fatura.class, idFatura);
        // +1
        Assert.notNull(fatura,"Não existe fatura para o id solicitado");
        // +1
        Assert.isTrue(calculaValorFatura().equals(fatura.retornaValorTotalFaturaCorrente().setScale(2)), "Parcelamento sugerido não da o valor total da fatura.");
        // +1
        List<Gastos> gastosFaturaCorrente = fatura.retornaGastosCorrentesAtuaisOrdenados();
        // +1
        FaturaCorrente faturaCorrente = new FaturaCorrente(fatura.getIdCartao(), gastosFaturaCorrente);

        return new ParcelaFatura(this.quantidade, this.valor, faturaCorrente);
    }

    public BigDecimal calculaValorFatura(){
        return this.valor.multiply(BigDecimal.valueOf(this.quantidade)).setScale(2);
    }

}
