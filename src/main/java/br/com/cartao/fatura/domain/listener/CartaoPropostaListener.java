package br.com.cartao.fatura.domain.listener;

import br.com.cartao.fatura.domain.model.Cartao;

public class CartaoPropostaListener {

    private String idCartao;
    private String idProposta;
    private String numeroCartao;

    @Deprecated
    public CartaoPropostaListener() {
    }

    public CartaoPropostaListener(String idCartao, String idProposta, String numeroCartao) {
        this.idCartao = idCartao;
        this.idProposta = idProposta;
        this.numeroCartao = numeroCartao;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public CartaoPropostaListener setIdCartao(String idCartao) {
        this.idCartao = idCartao;
        return this;
    }

    public CartaoPropostaListener setIdProposta(String idProposta) {
        this.idProposta = idProposta;
        return this;
    }

    public CartaoPropostaListener setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public Cartao toModel(){
        return new Cartao(this.idCartao, this.idProposta, this.numeroCartao);
    }
}
