package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.Gastos;
import br.com.cartao.fatura.utils.OfuscaDadosSensiveis;

import java.math.BigDecimal;
import java.util.List;

public class FaturaResponseDto {

    private String id;
    private String cartaoId;
    private BigDecimal valorTotal;
    private Integer numeroElementos;
    private List<Gastos> gastos;

    public FaturaResponseDto(Fatura fatura) {
        this.id = fatura.getId();
        this.valorTotal = fatura.retornaValorTotalFaturaCorrente();
        this.cartaoId= OfuscaDadosSensiveis.executa(fatura.getIdCartao());
        this.numeroElementos = fatura.numeroTotalDeCompras();
        this.gastos = fatura.getGastos();
    }

    public String getId() {
        return id;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
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
