package br.com.cartao.fatura.repository;

import br.com.cartao.fatura.domain.model.Fatura;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FaturaRepository extends CrudRepository<Fatura, String> {

    Optional<Fatura> findByIdCartao(String idCartao);

}
