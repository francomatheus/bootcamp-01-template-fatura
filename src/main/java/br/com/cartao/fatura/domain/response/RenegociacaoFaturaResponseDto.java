package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.model.RenegociacaoFatura;

public class RenegociacaoFaturaResponseDto {

    private String id;

    @Deprecated
    public RenegociacaoFaturaResponseDto() {
    }

    public RenegociacaoFaturaResponseDto(RenegociacaoFatura renegociacaoFatura) {
        this.id = renegociacaoFatura.getId();
    }

    public String getId() {
        return id;
    }

    public RenegociacaoFaturaResponseDto setId(String id) {
        this.id = id;
        return this;
    }
}
