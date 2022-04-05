import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GerenciadorDeArquivoJavaNio implements GerenciadorDeArquivo {

    @Override
    public void apagaArquivo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);

        if (arquivo.delete()) {
            // sucesso
        } else {
            // falha
        }
    }

    @Override
    public void apagaDiretorio(String caminhoDiretorio) {
        File diretorio = new File(caminhoDiretorio);

        if (diretorio.delete()) {
            // sucesso
        } else {
//            System.out.printf("Falhou");
            for (File arquivoFilho : Objects.requireNonNull(diretorio.listFiles())) {
                apagaDiretorio(arquivoFilho.getPath());
            }

            diretorio.delete();
        }
    }

    @Override
    public void criaArquivo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);

        try {
            arquivo.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void criaDiretorio(String caminhoDiretorio) {
        File diretorio = new File(caminhoDiretorio);
//        diretorio.mkdir();
        diretorio.mkdirs();
    }

    @Override
    public List<String> leLinhas(String caminhoArquivo) {
//        // Para ler qualquer tipo de arquivo:
        try(
            FileInputStream entrada = new FileInputStream(caminhoArquivo);
            FileChannel canalDeEntrada = entrada.getChannel()
        ) {

            ByteBuffer bytesLidos = ByteBuffer.allocate(10);

            int quantidadeDeBytesLidos = canalDeEntrada.read(bytesLidos);
            String texto = "";

            while (quantidadeDeBytesLidos != -1) {
                texto += (new String(bytesLidos.array())).substring(0, quantidadeDeBytesLidos);
                bytesLidos.clear();
                quantidadeDeBytesLidos = canalDeEntrada.read(bytesLidos);
            }

            return Arrays.stream(texto.split("\n")).toList();

        } catch(FileNotFoundException excecao) {
            System.out.println("Arquivo nao existe");
            return null;
        } catch (IOException excecao) {
            System.out.println("IOException");
            return null;
        }
    }

    @Override
    public void escreveLinhas(String caminhoArquivo, List<String> conteudoArquivo) {
        // Para escrever em qualquer tipo de arquivo:
        try (
            FileOutputStream saida = new FileOutputStream(caminhoArquivo);
            FileChannel canal = saida.getChannel();
        ) {
            ByteBuffer bytesParaEscrever = ByteBuffer.allocate(100);

            for (String linha : conteudoArquivo) {
                bytesParaEscrever.put(linha.getBytes());
                bytesParaEscrever.put("\n".getBytes());

                bytesParaEscrever.flip();
                canal.write(bytesParaEscrever);
                bytesParaEscrever.clear();
            }

            System.out.println("Sucesso");

        } catch(FileNotFoundException excecao) {
            System.out.println("Arquivo nao existe");
        } catch (IOException excecao) {
            System.out.println("IOException");
        }
    }
}
