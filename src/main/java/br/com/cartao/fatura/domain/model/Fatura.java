package br.com.cartao.fatura.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "fatura")
public class Fatura {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @OneToOne
    private Cartao cartao;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Gastos> gastos = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<FaturaCorrente> faturaCorrentes= new ArrayList<>();

    @Deprecated
    public Fatura() {
    }

    public Fatura(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public List<Gastos> getGastos() {
        return gastos;
    }

    public void adicionaGasto(Gastos gastos){
        this.gastos.add(gastos);
    }

    public List<Gastos> retornaGastosCorrentesAtuaisOrdenados(){
        List<Gastos> gastosOrdenadosPorData = this.gastos.stream()
                // +1
                .filter(gastos -> LocalDate.now().getMonth().equals(gastos.getEfetivadaEm().getMonth()))
                // +1
                .sorted(Gastos::compareTo)
                // +1
                .collect(Collectors.toList());

        return gastosOrdenadosPorData;
    }

    public BigDecimal retornaValorTotalFaturaCorrente(){
        return this.gastos.stream()
                // +1
                .filter(gastos -> LocalDate.now().getMonth().equals(gastos.getEfetivadaEm().getMonth()))
                .map(gastos1 -> {return gastos1.getValor();})
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public Integer numeroTotalDeCompras() {
        return retornaGastosCorrentesAtuaisOrdenados().size();
    }

//    public void adicionaFaturaCorrente(FaturaCorrente faturaCorrente){
//        this.faturaCorrentes.add(faturaCorrente);
//    }

    public String numeroCartao(){
        return this.cartao.getNumeroCartao();
    }
}
