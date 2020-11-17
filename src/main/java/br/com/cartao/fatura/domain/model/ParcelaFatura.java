package br.com.cartao.fatura.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcelaFatura")
public class ParcelaFatura {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotNull
    private Integer quantidade;
    @NotNull
    private BigDecimal valor;
    @Column(unique = true)
    @NotNull
    private Integer mesCorrente = LocalDate.now().getMonthValue();

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private @Valid FaturaCorrente fatura;

    @Deprecated
    public ParcelaFatura() {
    }

    public ParcelaFatura(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal valor, @NotNull @Valid FaturaCorrente fatura) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.fatura = fatura;
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

    public Integer getMesCorrente() {
        return mesCorrente;
    }

    public FaturaCorrente getFatura() {
        return fatura;
    }

    public BigDecimal calculaValorFatura(){
        return this.valor.multiply(BigDecimal.valueOf(this.quantidade));
    }
}
