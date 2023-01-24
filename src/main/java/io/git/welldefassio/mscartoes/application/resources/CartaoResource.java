package io.git.welldefassio.mscartoes.application.resources;

import io.git.welldefassio.mscartoes.application.representation.CartaoSaveRequest;
import io.git.welldefassio.mscartoes.application.representation.CartoesPorClienteResponse;
import io.git.welldefassio.mscartoes.application.service.CartaoService;
import io.git.welldefassio.mscartoes.application.service.ClienteCartaoService;
import io.git.welldefassio.mscartoes.domain.Cartao;
import io.git.welldefassio.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartaoResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> catoesRendaMenorIgual = cartaoService.getCatoesRendaMenorIgual(renda);
        return ResponseEntity.ok(catoesRendaMenorIgual);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> clienteCartoes = clienteCartaoService.listCartaoByCpf(cpf);
        return ResponseEntity.ok(clienteCartoes.stream().map(CartoesPorClienteResponse::fromModel).toList());
    }


    @GetMapping
    public String status() {
        return "ok";
    }

}
