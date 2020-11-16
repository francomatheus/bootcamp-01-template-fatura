package br.com.cartao.fatura.domain.integration;

import java.time.LocalDateTime;

public class CarteirasResponseIntegracao {

    private String id;
    private String email;
    private LocalDateTime associadoEm;
    private String emissor;

    @Deprecated
    public CarteirasResponseIntegracao() {
    }

    public CarteirasResponseIntegracao(String id, String email, LocalDateTime associadoEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadoEm() {
        return associadoEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
