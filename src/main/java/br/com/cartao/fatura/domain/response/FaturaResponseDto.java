package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.Gastos;

import java.math.BigDecimal;
import java.util.List;

public class FaturaResponseDto {

    private String id;
    private BigDecimal valorTotal;
    private Integer numeroElementos;
    private CartaoResponseDto cartao;
    private List<Gastos> gastos;

    public FaturaResponseDto(Fatura fatura) {
        this.id = fatura.getId();
        this.valorTotal = fatura.retornaValorTotalFaturaCorrente();
        this.cartao= new CartaoResponseDto(fatura.getCartao());
        this.numeroElementos = fatura.numeroTotalDeCompras();
        this.gastos = fatura.getGastos();
    }

    public String getId() {
        return id;
    }

    public CartaoResponseDto getCartao() {
        return cartao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public List<Gastos> getGastos() {
        return gastos;
    }

    public Integer getNumeroElementos() {
        return numeroElementos;
    }
}
