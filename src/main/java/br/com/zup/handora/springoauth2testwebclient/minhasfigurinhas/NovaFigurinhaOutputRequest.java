package br.com.zup.handora.springoauth2testwebclient.minhasfigurinhas;

public class NovaFigurinhaOutputRequest {

    private final String descricao;
    private final String enderecoDaImagem;

    public NovaFigurinhaOutputRequest(NovaFigurinhaInputRequest request) {
        this.descricao = request.getDescricao();
        this.enderecoDaImagem = request.getEnderecoDaImagem();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEnderecoDaImagem() {
        return enderecoDaImagem;
    }

}
