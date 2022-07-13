package br.com.zup.handora.springoauth2testwebclient.minhasfigurinhas;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class MinhasFigurinhasClient {

    public final static String BASE_URI = "http://localhost:8080/oauth2-resourceserver-minhas-figurinhas/api";

    private final WebClient webClient;

    public MinhasFigurinhasClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<Void> cadastrarAlbum(NovoAlbumOutputRequest outputRequest) {
        return webClient.post()
                        .uri(BASE_URI + "/albuns")
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(outputRequest), NovoAlbumOutputRequest.class)
                        .attributes(
                            ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(
                                "minhas-figurinhas"
                            )
                        )
                        .retrieve()
                        .toBodilessEntity()
                        .block();
    }

    public DetalhesDoAlbumInputResponse detalharAlbum(Long albumId) {
        return webClient.get()
                        .uri(BASE_URI + "/albuns" + "/" + albumId)
                        .attributes(
                            ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(
                                "minhas-figurinhas"
                            )
                        )
                        .retrieve()
                        .bodyToMono(
                            new ParameterizedTypeReference<DetalhesDoAlbumInputResponse>() {}
                        )
                        .block();
    }

}
