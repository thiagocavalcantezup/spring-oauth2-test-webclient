package br.com.zup.handora.springoauth2testwebclient.minhasfigurinhas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

public class NovaFigurinhaInputRequest {

    @NotBlank
    @Size(max = 2000)
    private String descricao;

    @URL
    @NotNull
    private String enderecoDaImagem;

    public NovaFigurinhaInputRequest() {}

    public NovaFigurinhaInputRequest(@NotBlank @Size(max = 2000) String descricao,
                                     @URL @NotNull String enderecoDaImagem) {
        this.descricao = descricao;
        this.enderecoDaImagem = enderecoDaImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEnderecoDaImagem() {
        return enderecoDaImagem;
    }

}
