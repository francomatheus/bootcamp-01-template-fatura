package br.com.cartao.fatura.domain.integration;

import java.time.LocalDate;

public class AvisosResponseIntegracao {

    private LocalDate validoAte;
    private String destino;

    @Deprecated
    public AvisosResponseIntegracao() {
    }

    public AvisosResponseIntegracao(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
