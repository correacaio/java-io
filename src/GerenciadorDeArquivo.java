import java.util.List;

public interface GerenciadorDeArquivo {

    void apagaArquivo(String caminhoArquivo);

    void apagaDiretorio(String caminhoDiretorio);

    void criaArquivo(String caminhoArquivo);

    void criaDiretorio(String caminhoDiretorio);

    List<String> leLinhas(String caminhoArquivo);

    void escreveLinhas(String caminhoArquivo, List<String> conteudoArquivo);
}
