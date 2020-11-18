package br.com.cartao.fatura.domain.listener;

public class CartaoResponseListener {

    private String id;
    private String email;

    @Deprecated
    public CartaoResponseListener() {
    }

    public CartaoResponseListener(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public CartaoResponseListener setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CartaoResponseListener setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
