package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.Gastos;

import java.util.List;

public class FaturaResponseDto {

    private String id;
    private String cartaoId;
    private List<Gastos> gastos;

    public FaturaResponseDto(Fatura fatura) {
        int idCartaoSize = fatura.getIdCartao().length();
        this.id = fatura.getId();
        this.cartaoId= "******************".concat(fatura.getIdCartao().substring(idCartaoSize-4, idCartaoSize));
        this.gastos = fatura.getGastos();
    }

    public String getId() {
        return id;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public List<Gastos> getGastos() {
        return gastos;
    }

    @Override
    public String toString() {
        return "FaturaResponseDto{" +
                "id='" + id + '\'' +
                ", cartaoId='" + cartaoId + '\'' +
                ", gastos=" + gastos +
                '}';
    }
}
