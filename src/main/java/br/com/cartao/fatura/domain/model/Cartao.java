package br.com.cartao.fatura.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartao")
public class Cartao {

    @Id
    private String idCartao;
    private String idProposta;
    private String numeroCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String idCartao, String idProposta, String numeroCartao) {
        this.idCartao = idCartao;
        this.idProposta = idProposta;
        this.numeroCartao = numeroCartao;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
