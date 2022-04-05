import java.util.List;

public class Main {

    public static void main(String[] args) {
        GerenciadorDeArquivo gerenciadorDeArquivo = new GerenciadorDeArquivoJavaNio2();
//        gerenciadorDeArquivo.criaArquivo("arquivo-em-branco.txt");
        gerenciadorDeArquivo.apagaArquivo("arquivo-nao-existente.txt");

//        gerenciadorDeArquivo.criaDiretorio("diretorio2/subdiretorio");
//        gerenciadorDeArquivo.apagaDiretorio("diretorio2");
//
//        List<String> linhas = gerenciadorDeArquivo.leLinhas("arquivo.txt");
//        gerenciadorDeArquivo.escreveLinhas("arquivo-novo.txt", linhas);
    }
}
