package br.com.cartao.fatura.domain.response;

import br.com.cartao.fatura.domain.integration.CartaoResponseIntegracao;
import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.model.Gastos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 9
 */

public class LimiteDisponivelResponseDto {

    private BigDecimal limiteCartao;

    private BigDecimal valorGasto;

    private BigDecimal limiteDisponivel;
    // +1
    private List<Gastos> ultimasTransacoes ;

    @Deprecated
    public LimiteDisponivelResponseDto() {
    }
    // +2
    public LimiteDisponivelResponseDto(CartaoResponseIntegracao cartaoResponseIntegracao, Optional<Fatura> faturaCartaoSolicitado) {
        this.limiteCartao = new BigDecimal(cartaoResponseIntegracao.getLimite());
        this.valorGasto = calculaGastosMesCorrente(faturaCartaoSolicitado);
        this.ultimasTransacoes = adicionaUltimosGastos(faturaCartaoSolicitado);
        this.limiteDisponivel = calculaLimiteDisponivel(cartaoResponseIntegracao, faturaCartaoSolicitado);
    }

    public BigDecimal getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public List<Gastos> getUltimasTransacoes() {
        return ultimasTransacoes;
    }

    public BigDecimal getLimiteCartao() {
        return limiteCartao;
    }

    public BigDecimal getValorGasto() {
        return valorGasto;
    }

    public List<Gastos> adicionaUltimosGastos(Optional<Fatura> faturaCartaoSolicitado){
        List<Gastos> ultimosGastos;
        // +1
        if (faturaCartaoSolicitado.isEmpty()){
            return new ArrayList<>();
        }

        List<Gastos> ultimosGastosOrdenadosPorData = faturaCartaoSolicitado.get().retornaGastosCorrentesAtuaisOrdenados();

        int tamanhoListaUltimosGastos = ultimosGastosOrdenadosPorData.size();

        ultimosGastos = ultimosGastosOrdenadosPorData;

        // +1
        if (tamanhoListaUltimosGastos>10){
            ultimosGastos = ultimosGastosOrdenadosPorData.subList(0, 10);
        }

        return ultimosGastos;
    }

    public BigDecimal calculaLimiteDisponivel(CartaoResponseIntegracao cartaoResponseIntegracao, Optional<Fatura> faturaCartaoSolicitado ){
        BigDecimal gastosTotais = calculaGastosMesCorrente(faturaCartaoSolicitado);
        return BigDecimal.valueOf(cartaoResponseIntegracao.getLimite()).subtract(gastosTotais);
    }

    public BigDecimal calculaGastosMesCorrente(Optional<Fatura> faturaCartaoSolicitado){
        List<Gastos> gastos = new ArrayList<>();
        // +1
        if (faturaCartaoSolicitado.isPresent()){
            gastos = faturaCartaoSolicitado.get().getGastos();
        }

        BigDecimal somaGastosDoMesCorrente = gastos.stream()
                // +1
                .filter(gasto -> LocalDate.now().getMonth().equals(gasto.getEfetivadaEm().getMonth()))
                // +1
                .map(gasto -> {
                    return gasto.getValor();
                })
                // +1
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return somaGastosDoMesCorrente;
    }


}
