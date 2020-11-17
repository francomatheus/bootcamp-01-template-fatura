package br.com.cartao.fatura.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faturaCorrente")
public class FaturaCorrente {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotBlank
    private String idCartao;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    List<Gastos> gastos = new ArrayList<>();

    @Deprecated
    public FaturaCorrente() {
    }

    public FaturaCorrente(String idCartao, List<Gastos> gastos) {
        this.idCartao = idCartao;
        this.gastos = gastos;
    }

    public String getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public List<Gastos> getGastos() {
        return gastos;
    }
}
