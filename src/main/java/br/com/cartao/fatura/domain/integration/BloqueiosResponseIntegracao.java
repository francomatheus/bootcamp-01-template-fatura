package br.com.cartao.fatura.domain.integration;

import java.time.LocalDateTime;

public class BloqueiosResponseIntegracao {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaresponsavel;
    private Boolean ativo;

    @Deprecated
    public BloqueiosResponseIntegracao() {
    }

    public BloqueiosResponseIntegracao(String id, LocalDateTime bloqueadoEm, String sistemaresponsavel, Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaresponsavel = sistemaresponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaresponsavel() {
        return sistemaresponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
