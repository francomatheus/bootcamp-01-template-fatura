package br.com.cartao.fatura.repository;

import br.com.cartao.fatura.domain.model.Fatura;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FaturaRepository extends PagingAndSortingRepository<Fatura, String> {

    Optional<Fatura> findByIdCartao(String idCartao);

}
