package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.model.Cartao;
import br.com.cartao.fatura.utils.OfuscaDadosSensiveis;

public class CartaoResponseDto {

    private String idCartao;
    private String idProposta;
    private String numeroCartao;

    @Deprecated
    public CartaoResponseDto() {
    }

    public CartaoResponseDto(Cartao cartao) {
        this.idCartao = cartao.getIdCartao();
        this.idProposta = cartao.getIdProposta();
        this.numeroCartao = OfuscaDadosSensiveis.executa(cartao.getNumeroCartao());
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
}
