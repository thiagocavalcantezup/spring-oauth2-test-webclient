package br.com.zup.handora.springoauth2testwebclient.livraria;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LivrariaClient {

    public final static String BASE_URI = "http://localhost:8080/oauth2-resourceserver-livraria/api";

    private final WebClient webClient;

    public LivrariaClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public DetalhesDoLivroResponse detalharLivro(Long livroId) {
        return webClient.get()
                        .uri(BASE_URI + "/livros" + "/" + livroId)
                        .attributes(
                            ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(
                                "livraria"
                            )
                        )
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<DetalhesDoLivroResponse>() {})
                        .block();
    }

    public DetalhesDoAutorResponse detalharAutor(Long autorId) {
        return webClient.get()
                        .uri(BASE_URI + "/autores" + "/" + autorId)
                        .attributes(
                            ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(
                                "livraria"
                            )
                        )
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<DetalhesDoAutorResponse>() {})
                        .block();
    }

}
