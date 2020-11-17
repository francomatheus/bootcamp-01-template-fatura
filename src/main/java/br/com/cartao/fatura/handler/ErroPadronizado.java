package br.com.cartao.fatura.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErroPadronizado {

    private LocalDateTime instante;
    private HttpStatus httpStatus;
    private String causa;

    @Deprecated
    public ErroPadronizado() {
    }

    public ErroPadronizado(HttpStatus httpStatus, String causa) {
        this.instante = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.causa = causa;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCausa() {
        return causa;
    }
}
