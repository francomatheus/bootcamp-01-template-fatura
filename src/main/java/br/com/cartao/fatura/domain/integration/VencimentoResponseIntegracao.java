package br.com.cartao.fatura.domain.integration;

import java.time.LocalDateTime;

public class VencimentoResponseIntegracao {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public VencimentoResponseIntegracao() {
    }

    public VencimentoResponseIntegracao(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
