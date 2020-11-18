package br.com.cartao.fatura.repository;

import br.com.cartao.fatura.domain.model.ParcelaFatura;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParcelaFaturaRepository extends CrudRepository<ParcelaFatura, String> {

    List<ParcelaFatura> findAllByAvisoLegadoIsFalse();
}
