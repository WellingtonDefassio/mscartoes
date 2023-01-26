package io.git.welldefassio.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.git.welldefassio.mscartoes.domain.Cartao;
import io.git.welldefassio.mscartoes.domain.ClienteCartao;
import io.git.welldefassio.mscartoes.domain.DadosEmissaoCartao;
import io.git.welldefassio.mscartoes.infra.repositories.CartaoRespository;
import io.git.welldefassio.mscartoes.infra.repositories.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoSubscriber {

    private final CartaoRespository cartaoRespository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            DadosEmissaoCartao dadosEmissaoCartao = mapper.readValue(payload, DadosEmissaoCartao.class);
            Cartao cartao = cartaoRespository.findById(dadosEmissaoCartao.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dadosEmissaoCartao.getCpf());
            clienteCartao.setLimite(dadosEmissaoCartao.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);
        } catch (Exception e) {
           log.error("Error ao receber solicitação de emissão cartão: {} ", e.getMessage());
        }
    }
}
