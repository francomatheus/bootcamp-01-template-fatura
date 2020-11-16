package br.com.cartao.fatura.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    private String idCartao;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    List<Gastos> gastos = new ArrayList<>();

    @Deprecated
    public Fatura() {
    }

    public Fatura(String idCartao) {
        this.idCartao = idCartao;
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

    public void adicionaGasto(Gastos gastos){
        this.gastos.add(gastos);
    }

    public List<Gastos> ultimosGastosOrdenadosPorData(){

        List<Gastos> gastosOrdenadosPorData = this.gastos.stream()
                // +1
                .filter(gastos -> LocalDate.now().getMonth().equals(gastos.getEfetivadaEm().getMonth()))
                // +1
                .sorted(Gastos::compareTo)
                // +1
                .collect(Collectors.toList());

        return gastosOrdenadosPorData;
    }
}
