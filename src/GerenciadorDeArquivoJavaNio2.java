import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GerenciadorDeArquivoJavaNio2 implements GerenciadorDeArquivo {

    @Override
    public void apagaArquivo(String caminhoArquivo) {
        Path caminho = Path.of(caminhoArquivo);

        try {
            Files.deleteIfExists(caminho);
        } catch (NoSuchFileException excecao) {
            // sucesso
        } catch (IOException excecao) {
            System.out.println();
        }
    }

    @Override
    public void apagaDiretorio(String caminhoDiretorio) {
        try {
             Files.delete(Path.of(caminhoDiretorio));
        } catch (IOException excecao) { }
    }

    @Override
    public void criaArquivo(String caminhoArquivo) {
        Path caminho = Path.of(caminhoArquivo);

        try {
            Files.createFile(caminho);
        } catch (IOException excecao) {

        }

    }

    @Override
    public void criaDiretorio(String caminhoDiretorio) {
//        Path caminho = Path.of("diretorio-nio2");
//        Path caminhoCompleto = caminho.resolve("subdiretorio-nio2");
//
//        Path caminhoCompleto2 = Path.of("diretorio-nio2/../diretorio-nio2/subdiretorio-nio2");
//        caminho.normalize();

        Path caminho = Path.of(caminhoDiretorio);

        try {
            Files.createDirectories(caminho);
        } catch (IOException excecao) { }
    }

    @Override
    public List<String> leLinhas(String caminhoArquivo) {
        Path caminho = Paths.get(caminhoArquivo);

        try {
            return Files.readAllLines(caminho);
            // Files.lines(caminho)
        } catch (IOException excecao) {
            return null;
        }
    }

    @Override
    public void escreveLinhas(String caminhoArquivo, List<String> conteudoArquivo) {
        Path caminho = Paths.get(caminhoArquivo);

        try {
            Files.write(caminho, conteudoArquivo);
        } catch (IOException excecao) { }
    }
}
