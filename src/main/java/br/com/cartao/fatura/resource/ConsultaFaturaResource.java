package br.com.cartao.fatura.resource;

import br.com.cartao.fatura.domain.model.Fatura;
import br.com.cartao.fatura.domain.response.FaturaResponseDto;
import br.com.cartao.fatura.repository.FaturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/faturas")
public class ConsultaFaturaResource {

    private static Logger logger = LoggerFactory.getLogger(ConsultaFaturaResource.class);

    private final FaturaRepository faturaRepository;

    public ConsultaFaturaResource(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultaFaturaCorrente(@PathVariable(value = "id",required = true) String idCartao){
        logger.info("Requisição para consultar fatura recebida, para o cartão com final: {}", idCartao.substring(idCartao.length()-4, idCartao.length()));

        Optional<Fatura> faturaBuscadaPeloIdCartao = faturaRepository.findByIdCartao(idCartao);

        if (faturaBuscadaPeloIdCartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Fatura não encontrada para idCartao solicitado.");
        }

        FaturaResponseDto faturaResponseDto =  new FaturaResponseDto(faturaBuscadaPeloIdCartao.get());

        return ResponseEntity.status(HttpStatus.OK). body(faturaResponseDto);
    }

}
