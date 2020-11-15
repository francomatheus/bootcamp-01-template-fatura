package br.com.cartao.fatura.domain.listener;

public class CartaoListener {

    private String id;
    private String email;

    @Deprecated
    public CartaoListener() {
    }

    public CartaoListener(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public CartaoListener setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CartaoListener setEmail(String email) {
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
