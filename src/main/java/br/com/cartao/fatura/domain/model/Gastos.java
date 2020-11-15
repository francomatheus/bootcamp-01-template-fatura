package br.com.cartao.fatura.domain.model;

import br.com.cartao.fatura.domain.listener.TransacaoCartaoListener;
import br.com.cartao.fatura.listeners.TransacaoListener;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos")
public class Gastos {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    private String idTransacaoLegado;
    @NotNull
    private BigDecimal valor;
    @Embedded
    private EstabelecimentoCompra estabelecimento;
    @NotBlank
    private String email;
    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Gastos() {
    }

    public Gastos(@NotBlank String idTransacaoLegado, @NotNull BigDecimal valor, EstabelecimentoCompra estabelecimento, @NotBlank String email, @NotNull LocalDateTime efetivadaEm) {
        this.idTransacaoLegado = idTransacaoLegado;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.email = email;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public Gastos setId(String id) {
        this.id = id;
        return this;
    }

    public String getIdTransacaoLegado() {
        return idTransacaoLegado;
    }

    public Gastos setIdTransacaoLegado(String idTransacaoLegado) {
        this.idTransacaoLegado = idTransacaoLegado;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Gastos setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public EstabelecimentoCompra getEstabelecimento() {
        return estabelecimento;
    }

    public Gastos setEstabelecimento(EstabelecimentoCompra estabelecimento) {
        this.estabelecimento = estabelecimento;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Gastos setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Gastos setEfetivadaEm(LocalDateTime efetivadaEm) {
        this.efetivadaEm = efetivadaEm;
        return this;
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "id='" + id + '\'' +
                ", idTransacaoLegado='" + idTransacaoLegado + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", email='" + email + '\'' +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }
}
