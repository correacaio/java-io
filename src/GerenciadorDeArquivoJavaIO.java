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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenciadorDeArquivoJavaIO implements GerenciadorDeArquivo {

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
            for (File arquivoFilho : diretorio.listFiles()) {
                arquivoFilho.delete();
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
//        try(
//            InputStream entrada = new FileInputStream(caminhoArquivo);
//            BufferedInputStream entradaComBuffer = new BufferedInputStream(entrada)
//        ) {
//
//            byte[] bytesLidos = new byte[10];
//
//            int quantidadeDeBytesLidos = entradaComBuffer.read(bytesLidos);
//            String texto = "";
//
//            while(quantidadeDeBytesLidos != -1) {
//                texto += (new String(bytesLidos)).substring(0, quantidadeDeBytesLidos);
//                quantidadeDeBytesLidos = entradaComBuffer.read(bytesLidos);
//            }
//
//            return Arrays.stream(texto.split("\n")).toList();
//
//        } catch(FileNotFoundException excecao) {
//            System.out.println("Arquivo nao existe");
//            return null;
//        } catch (IOException excecao) {
//            System.out.println("IOException");
//            return null;
//        }

        // Para ler arquivos texto (.txt, .csz, ...):
        try(
            FileReader entrada = new FileReader(caminhoArquivo);
            BufferedReader entradaComBuffer = new BufferedReader(entrada)
        ) {
            String linha = entradaComBuffer.readLine();
            List<String> linhas = new ArrayList<>();

            while(linha != null) {
                linhas.add(linha);
                linha = entradaComBuffer.readLine();
            }

            return linhas;

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
//        // Para escrever em qualquer tipo de arquivo:
//        try (
//            OutputStream saida = new FileOutputStream(caminhoArquivo);
//            BufferedOutputStream saidaComBuffer = new BufferedOutputStream(saida)
//        ) {
//            for (String linha : conteudoArquivo) {
//                saidaComBuffer.write(linha.getBytes());
//                saidaComBuffer.write('\n');
//            }
//
//            saidaComBuffer.flush();
//
//        } catch(FileNotFoundException excecao) {
//            System.out.println("Arquivo nao existe");
//        } catch (IOException excecao) {
//            System.out.println("IOException");
//        }

        // Para escrever em arquivos texto (.txt, .csz, ...):
        try (
            FileWriter saida = new FileWriter(caminhoArquivo, true); // para concatenar
            BufferedWriter saidaComBuffer = new BufferedWriter(saida)
        ) {
            for (String linha : conteudoArquivo) {
                saidaComBuffer.write(linha);
                saidaComBuffer.newLine();
            }

            saidaComBuffer.flush();

        } catch(FileNotFoundException excecao) {
            System.out.println("Arquivo nao existe");
        } catch (IOException excecao) {
            System.out.println("IOException");
        }
    }
}
