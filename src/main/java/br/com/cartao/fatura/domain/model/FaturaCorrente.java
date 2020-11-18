package br.com.cartao.fatura.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faturaCorrente")
public class FaturaCorrente {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToOne
    private Cartao cartao;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    List<Gastos> gastos = new ArrayList<>();

    @Deprecated
    public FaturaCorrente() {
    }

    public FaturaCorrente(Cartao cartao, List<Gastos> gastos) {
        this.cartao = cartao;
        this.gastos = gastos;
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

    public String numeroCartao(){
        return this.cartao.getNumeroCartao();
    }
}
