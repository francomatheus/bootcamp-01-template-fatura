package br.com.cartao.fatura.domain.listener;

import br.com.cartao.fatura.domain.model.Gastos;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 4
 */

public class TransacaoCartaoListener {

    private String id;

    private BigDecimal valor;
    // +1
    private EstabelecimentoCompraListener estabelecimento;
    // +1
    private CartaoResponseListener cartao;
    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss", shape = JsonFormat.Shape.STRING)
    private String efetivadaEm;

    public TransacaoCartaoListener() {
    }

    public TransacaoCartaoListener(String id, BigDecimal valor, EstabelecimentoCompraListener estabelecimento, CartaoResponseListener cartao, String efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public TransacaoCartaoListener setId(String id) {
        this.id = id;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TransacaoCartaoListener setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public EstabelecimentoCompraListener getEstabelecimento() {
        return estabelecimento;
    }

    public TransacaoCartaoListener setEstabelecimento(EstabelecimentoCompraListener estabelecimento) {
        this.estabelecimento = estabelecimento;
        return this;
    }

    public CartaoResponseListener getCartao() {
        return cartao;
    }

    public TransacaoCartaoListener setCartao(CartaoResponseListener cartao) {
        this.cartao = cartao;
        return this;
    }

    public String getEfetivadaEm() {
        return efetivadaEm;
    }

    public TransacaoCartaoListener setEfetivadaEm(String efetivadaEm) {
        this.efetivadaEm = efetivadaEm;
        return this;
    }

    @Override
    public String toString() {
        return "TransacaoCartaoEvento{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

    public Gastos toGastos() {
        return new Gastos(this.id,this.valor,this.estabelecimento.toModel(),this.cartao.getEmail(),LocalDateTime.parse(this.efetivadaEm));
    }

    public String getIdCartao(){
        return this.cartao.getId();
    }
}


