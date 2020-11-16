package br.com.cartao.fatura.domain.integration;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CartaoResponseIntegracao {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private Set<BloqueiosResponseIntegracao> bloqueios = new HashSet<>();
    private Set<AvisosResponseIntegracao> avisos = new HashSet<>();
    private Set<CarteirasResponseIntegracao> carteiras = new HashSet<>();
    private Set<ParcelasResponseIntegracao> parcelas = new HashSet<>();
    private Integer limite;
    private RenegociacaoResponseIntegracao renegociacao;
    private VencimentoResponseIntegracao vencimento;
    private String idProposta;

    @Deprecated
    public CartaoResponseIntegracao() {
    }

    public CartaoResponseIntegracao(String id, LocalDateTime emitidoEm, String titular, Set<BloqueiosResponseIntegracao> bloqueios, Set<AvisosResponseIntegracao> avisos, Set<CarteirasResponseIntegracao> carteiras, Set<ParcelasResponseIntegracao> parcelas, Integer limite, RenegociacaoResponseIntegracao renegociacao, VencimentoResponseIntegracao vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Set<BloqueiosResponseIntegracao> getBloqueios() {
        return bloqueios;
    }

    public Set<AvisosResponseIntegracao> getAvisos() {
        return avisos;
    }

    public Set<CarteirasResponseIntegracao> getCarteiras() {
        return carteiras;
    }

    public Set<ParcelasResponseIntegracao> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public RenegociacaoResponseIntegracao getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponseIntegracao getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
