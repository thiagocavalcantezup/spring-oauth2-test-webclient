package br.com.zup.handora.springoauth2testwebclient.gestaofuncionarios;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GestaoFuncionariosClient {

    public final static String BASE_URI = "http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios";

    private final WebClient webClient;

    public GestaoFuncionariosClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<FuncionarioResponse> listar() {
        return webClient.get()
                        .uri(BASE_URI)
                        .attributes(
                            ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(
                                "gestao-funcionarios"
                            )
                        )
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<FuncionarioResponse>>() {})
                        .block();
    }

}
