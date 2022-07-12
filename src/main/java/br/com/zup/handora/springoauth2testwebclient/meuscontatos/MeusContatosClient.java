package br.com.zup.handora.springoauth2testwebclient.meuscontatos;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MeusContatosClient {

    public final static String BASE_URI = "http://localhost:8080/api/contatos";

    private final WebClient webClient;

    public MeusContatosClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ContatoResponse> listar() {
        return webClient.get()
                        .uri(BASE_URI)
                        .attributes(
                            ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(
                                "meus-contatos"
                            )
                        )
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<ContatoResponse>>() {})
                        .block();
    }

}
