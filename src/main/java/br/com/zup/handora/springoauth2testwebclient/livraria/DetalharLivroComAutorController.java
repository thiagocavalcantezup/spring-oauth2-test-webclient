package br.com.zup.handora.springoauth2testwebclient.livraria;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(DetalharLivroComAutorController.BASE_URI)
public class DetalharLivroComAutorController {

    public final static String BASE_URI = "/livraria";

    private final LivrariaClient livrariaClient;

    public DetalharLivroComAutorController(LivrariaClient livrariaClient) {
        this.livrariaClient = livrariaClient;
    }

    @GetMapping("/livros/{livroId}")
    public ResponseEntity<?> detalharLivroComAutor(@PathVariable Long livroId) {
        DetalhesDoLivroResponse detalhesDoLivroResponse;
        DetalhesDoAutorResponse detalhesDoAutorResponse;

        try {
            detalhesDoLivroResponse = livrariaClient.detalharLivro(livroId);
        } catch (WebClientResponseException.NotFound e) {
            throw new ResponseStatusException(NOT_FOUND, "Livro não encontrado");
        }

        try {
            detalhesDoAutorResponse = livrariaClient.detalharAutor(
                detalhesDoLivroResponse.getAutorId()
            );
        } catch (WebClientResponseException.NotFound e) {
            throw new ResponseStatusException(NOT_FOUND, "Autor não encontrado");
        }

        return ResponseEntity.ok(
            new DetalhesDoLivroComAutorResponse(detalhesDoLivroResponse, detalhesDoAutorResponse)
        );
    }

}
