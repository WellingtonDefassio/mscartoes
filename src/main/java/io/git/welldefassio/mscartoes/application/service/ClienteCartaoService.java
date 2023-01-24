package io.git.welldefassio.mscartoes.application.service;

import io.git.welldefassio.mscartoes.domain.ClienteCartao;
import io.git.welldefassio.mscartoes.repositories.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    public List<ClienteCartao> listCartaoByCpf(String cpf) {
        return clienteCartaoRepository.findByCpf(cpf);
    }


}
