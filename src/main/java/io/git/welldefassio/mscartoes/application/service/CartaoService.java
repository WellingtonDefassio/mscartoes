package io.git.welldefassio.mscartoes.application.service;

import io.git.welldefassio.mscartoes.domain.Cartao;
import io.git.welldefassio.mscartoes.infra.repositories.CartaoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRespository cartaoRespository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return cartaoRespository.save(cartao);
    }

    public List<Cartao> getCatoesRendaMenorIgual(Long renda) {
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
        return cartaoRespository.findByRendaLessThanEqual(rendaBigDecimal);
    }

}
