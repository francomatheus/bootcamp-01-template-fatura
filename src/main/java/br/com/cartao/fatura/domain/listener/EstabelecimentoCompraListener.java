package br.com.cartao.fatura.domain.listener;


import br.com.cartao.fatura.domain.model.EstabelecimentoCompra;

public class EstabelecimentoCompraListener {

    private String nome;
    private String cidade;
    private String endereco;

    @Deprecated
    public EstabelecimentoCompraListener() {
    }

    public EstabelecimentoCompraListener(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public EstabelecimentoCompraListener setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public EstabelecimentoCompraListener setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public EstabelecimentoCompraListener setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    @Override
    public String toString() {
        return "EstabelecimentoCompra{" +
                "nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public EstabelecimentoCompra toModel(){
        return new EstabelecimentoCompra(this.nome,this.cidade, this.endereco);
    }
}
