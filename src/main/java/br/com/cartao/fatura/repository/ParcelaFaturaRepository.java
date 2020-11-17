package br.com.cartao.fatura.repository;

import br.com.cartao.fatura.domain.model.ParcelaFatura;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParcelaFaturaRepository extends CrudRepository<ParcelaFatura, String> {

    Optional<ParcelaFatura> findByMesCorrenteAndFaturaIdCartao(Integer mesCorrente, String idCartao);
}
