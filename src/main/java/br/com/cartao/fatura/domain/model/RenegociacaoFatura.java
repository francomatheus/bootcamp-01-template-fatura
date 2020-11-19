package br.com.cartao.fatura.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "renegociacaoFatura")
public class RenegociacaoFatura {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull @Positive
    private final Integer quantidade;
    @NotNull @Positive
    private final BigDecimal valor;
    @NotNull @Future
    private final LocalDateTime inicioPagamento;
    @ManyToOne
    private final Cartao cartao;
    @ManyToOne
    private final Fatura fatura;
    @OneToOne(cascade = CascadeType.PERSIST)
    private final FaturaCorrente faturaCorrente;

    public RenegociacaoFatura(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal valor, Cartao cartao, Fatura fatura, FaturaCorrente faturaCorrente) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = cartao;
        this.fatura = fatura;
        this.faturaCorrente = faturaCorrente;
        this.inicioPagamento = LocalDateTime.now().plusDays(120);
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

    public LocalDateTime getInicioPagamento() {
        return inicioPagamento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Fatura getFatura() {
        return fatura;
    }
}
