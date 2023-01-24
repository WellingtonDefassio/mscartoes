package io.git.welldefassio.mscartoes.application.representation;

import io.git.welldefassio.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartoesPorClienteResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteResponse fromModel(ClienteCartao clienteCartao) {
        return new CartoesPorClienteResponse(clienteCartao.getCartao().getNome(),
                clienteCartao.getCartao().getBandeira().toString(),
                clienteCartao.getLimite());
    }

}
