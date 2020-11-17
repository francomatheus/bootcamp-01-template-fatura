package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.model.ParcelaFatura;

public class ParcelarFaturaResponseDto {
    private String id;

    @Deprecated
    public ParcelarFaturaResponseDto() {
    }

    public ParcelarFaturaResponseDto(ParcelaFatura parcelaFatura) {
        this.id = parcelaFatura.getId();
    }

    public String getId() {
        return id;
    }

    public ParcelarFaturaResponseDto setId(String id) {
        this.id = id;
        return this;
    }
}
