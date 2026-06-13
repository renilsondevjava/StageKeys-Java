package com.renilson.stagekeys;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.renilson.stagekeys.service.ArquivoService;
import com.renilson.stagekeys.service.MusicaService;
import java.util.Collections;
import java.util.Comparator;
import com.renilson.stagekeys.service.RepertorioService;


public class Menu {

    //Scanner usado para capturar dados digitados pelo usuário
    private Scanner sc = new Scanner(System.in);

    private ArquivoService arquivoService =
            new ArquivoService();

    private MusicaService musicaService =
            new MusicaService();

    public ArrayList<Musica> getMusicas() {

        return musicas;

    }

    private RepertorioService repertorioService =
            new RepertorioService();

    //lista geral de músicas do sistema
    private ArrayList<Musica> musicas =
            new ArrayList<>();

    //lista geral de repertorios do sistema
    private ArrayList<Repertorio> repertorios =
            new ArrayList<>();

    public ArrayList<Repertorio> getRepertorios(){

        return repertorios;

    }

    public void carregarDados(){

        repertorios.clear();

        musicas.clear();

        arquivoService.carregarRepertoriosDoArquivo(
                repertorios
        );

        arquivoService.carregarMusicasDoArquivo(
                musicas
        );

        arquivoService.carregarRelacionamentos(
                repertorios,
                musicas
        );

    }

    public void listarRepertorios(){

        repertorioService.listarRepertorios(
                repertorios
        );

    }

    //busca repertório pelo ID e lista músicas dele
    public void buscarRepertorio() {

        System.out.println(
                "Digite o id do repertório: "
        );

        int idRepertorio = lerInt();

        Repertorio repertorio =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        idRepertorio
                );

        if (repertorio != null) {

            System.out.println(
                    "Encontrado: "
                            + repertorio.getNome()
            );

            repertorio.ordenarMusicasPorNome();

            for (Musica musica : repertorio.getMusicas()) {

                System.out.println(
                        "Música: " + musica.getNome()
                                + " | Artista: "
                                + musica.getArtista()
                                + " | Tom: "
                                + musica.getTom()
                );

            }

        } else {

            System.out.println(
                    "Repertório não encontrado."
            );

        }

    }

    //adiciona novo repertório
    public void adicionarRepertorio() {

        int novoId =
                repertorioService.gerarNovoId(
                        repertorios
                );

        //limpa ENTER do Scanner
        sc.nextLine();

        //pede nome do repertório
        System.out.println("Digite o nome do repertório:");

        String novoNome = sc.nextLine();

        //cria novo objeto Repertorio
        Repertorio novoRepertorio = new Repertorio(
                novoId,
                novoNome
        );

        //adiciona repertório na lista
        repertorios.add(novoRepertorio);

        System.out.println("Repertório adicionado com sucesso!");
    }

    //adiciona musica ao repertório
    public void adicionarMusica() {
        // pede o ID do repertório onde a música será adicionada
        System.out.println("Digite o ID do repertório:");

        // variável temporária começa vazia
        int idRepertorioMusica = lerInt();

        //método auxiliar responsável por buscar repertório pelo ID
        Repertorio repertorioEncontrado
                =  repertorioService.buscarRepertorioPorId(
                repertorios,
                idRepertorioMusica
        );

        // verifica se encontrou algum repertório
        if (repertorioEncontrado != null) {
            int idMusica =
                    musicaService.gerarNovoId(musicas);
            //verifica se já existe música com mesmo ID
            Musica musicaExistente =
                    musicaService.buscarMusicaPorId(
                            musicas,idMusica);

            //se encontrou música com mesmo ID
            if (musicaExistente != null) {

                System.out.println(
                        "Já existe uma música com esse ID!"
                );

                return;

            }

            // limpa o ENTER que ficou preso no Scanner
            sc.nextLine();

            // pede nome da música
            System.out.println("Digite o nome da música:");
            String nomeMusica = sc.nextLine();

            // pede nome do artista
            System.out.println("Digite o artista:");
            String artista = sc.nextLine();

            // pede o tom da musica
            System.out.println("Digite o tom:");
            String tom = sc.nextLine();

            // cria novo objeto Musica usando os dados digitados
            Musica novaMusica = new Musica(
                    idMusica,
                    nomeMusica,
                    artista,
                    tom
            );

            // adiciona música na lista geral de músicas
            musicas.add(novaMusica);

            boolean adicionou =
                    repertorioEncontrado.adicionarMusica(novaMusica);

            if (adicionou){

                System.out.println(
                        "Música adicionada com sucesso!"
                );

            } else {

                System.out.println(
                        "Essa música já existe no repertório!"
                );

            }

        } else {
            // executa se nenhum repertório for encontrado
            System.out.println("Repertório não encontrado!");

        }
    }

    public void adicionarMusicaExistenteAoRepertorio() {

        System.out.println(
                "Digite o ID do repertório:"
        );

        int idRepertorio = lerInt();

        Repertorio repertorio =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        idRepertorio
                );

        if (repertorio == null) {

            System.out.println(
                    "Repertório não encontrado."
            );

            return;

        }

        System.out.println(
                "Digite o ID da música:"
        );

        int idMusica = lerInt();

        Musica musica =
                musicaService.buscarMusicaPorId(
                        musicas,
                        idMusica
                );

        if (musica == null) {

            System.out.println(
                    "Música não encontrada."
            );

            return;

        }

        boolean adicionou =
                repertorio.adicionarMusica(musica);

        if (adicionou){

            System.out.println(
                    "Música adicionada ao repertório!"
            );

        } else {

            System.out.println(
                    "Essa música já está no repertório!"
            );

        }

    }

    //metodo para remover musica
    public void removerMusica() {
        System.out.println("Digite o id da musica: ");
        int id = lerInt();

        //usa método auxiliar para buscar musica
        Musica musicaRemover =
                musicaService.buscarMusicaPorId(
                        musicas,
                        id
                );

        //verifica se encontrou musica
        if (musicaRemover != null) {

            for (Repertorio repertorio : repertorios) {

                repertorio.getMusicas()
                        .remove(musicaRemover);

            }

            //remove musica da lista
            musicas.remove(musicaRemover);

            System.out.println("Musica " + musicaRemover.getNome() + " removido com sucesso!");
        } else {
            System.out.println("Musica não encontrado.");
        }

    }

    //busca música pelo ID
    public void buscarMusica() {

        //pede ID da música
        System.out.println("Digite o id da musica: ");

        int idMusica = lerInt();

        //usa método auxiliar
        Musica musica =
                musicaService.buscarMusicaPorId(
                        musicas,
                        idMusica
                );

        //verifica se encontrou música
        if (musica != null) {

            //exibe música encontrada
            System.out.println(
                    "Encontrado: " + musica.getNome()
            );

        } else {

            System.out.println("Música não encontrada.");

        }

    }

    public void buscarMusicaPorNome(){

        sc.nextLine();

        System.out.println(
                "Digite o nome da música:"
        );

        String nomeBusca =
                sc.nextLine().toLowerCase();

        boolean encontrou = false;

        for (Musica musica : musicas){

            if (musica.getNome()
                    .toLowerCase()
                    .contains(nomeBusca)){

                System.out.println(
                        "ID: " + musica.getId()
                                + " | Nome: "
                                + musica.getNome()
                                + " | Artista: "
                                + musica.getArtista()
                                + " | Tom: "
                                + musica.getTom()
                );

                encontrou = true;

            }

        }

        if (!encontrou){

            System.out.println(
                    "Nenhuma música encontrada."
            );

        }

    }

    public void buscarRepertorioPorNome(){

        sc.nextLine();

        System.out.println(
                "Digite o nome do repertório:"
        );

        String nomeBusca = sc.nextLine();

        repertorioService.buscarRepertorioPorNome(
                repertorios,
                nomeBusca
        );

    }


    public void ordenarRepertoriosPorNome(){

        repertorioService.ordenarRepertoriosPorNome(
                repertorios
        );

        System.out.println(
                "Repertórios ordenados!"
        );

    }

    public void ordenarMusicasDoRepertorio(){

        System.out.println(
                "Digite o ID do repertório:"
        );

        int id = lerInt();

        Repertorio repertorio =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        id
                );

        if (repertorio != null){

            repertorio.ordenarMusicasPorNome();

            System.out.println(
                    "Músicas do repertório ordenadas!"
            );

        } else {

            System.out.println(
                    "Repertório não encontrado!"
            );

        }

    }

    public void editarMusica() {
        //pede ID da música
        System.out.println("Digite o id da musica: ");

        int idMusica = lerInt();

        //libera o scanner
        sc.nextLine();

        Musica musicaEditar =
                musicaService.buscarMusicaPorId(
                        musicas,
                        idMusica
                );

        //verifica se encontrou musica
        if (musicaEditar != null) {
            System.out.println("Digite o novo nome: ");


            //cria variável pra receber o nome digitado pelo usuário
            String novoNome = sc.nextLine();

            //edita repertório encontrado
            musicaEditar.setNome(novoNome);
            System.out.println("Nome alterado para: " + musicaEditar.getNome());

        } else {
            System.out.println("musica não encontrada.");
        }

    }



    //método auxiliar para ler números inteiros com segurança
    public int lerInt(){

        //loop infinito até usuário digitar número válido
        while (true){

            //verifica se próxima entrada é número
            if (sc.hasNextInt()){

                //retorna número digitado
                return sc.nextInt();

            } else {

                //mensagem de erro
                System.out.println(
                        "Digite apenas números!"
                );

                //descarta valor inválido
                sc.nextLine();

            }

        }

    }


    //remover repertorio
    public void removerRepertorio() {
        System.out.println("Digite o id do repertorio: ");
        int id = lerInt();

        //usa método auxiliar para buscar repertório
        Repertorio repertorioRemover =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        id
                );

        //verifica se encontrou repertório
        if (repertorioRemover != null) {

            //remove repertório da lista
            repertorios.remove(repertorioRemover);

            System.out.println(
                    "Repertório "
                            + repertorioRemover.getNome()
                            + " removido com sucesso!"
            );
        } else {
            System.out.println("Repertório não encontrado.");
        }

    }
    public void removerMusicaDoRepertorio(){

        System.out.println(
                "Digite o ID do repertório:"
        );

        int idRepertorio = lerInt();

        Repertorio repertorio =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        idRepertorio);

        if (repertorio != null){

            System.out.println(
                    "Digite o ID da música:"
            );

            int idMusica = lerInt();

            boolean removeu =
                    repertorio.removerMusica(idMusica);

            if (removeu){

                System.out.println(
                        "Música removida do repertório!"
                );

            } else {

                System.out.println(
                        "Música não encontrada no repertório!"
                );

            }

        } else {

            System.out.println(
                    "Repertório não encontrado!"
            );

        }

    }

    public void listarMusicasSemRepertorio(){

        boolean encontrou = false;

        for (Musica musica : musicas){

            boolean estaEmRepertorio = false;

            for (Repertorio repertorio : repertorios){

                if (repertorio.getMusicas().contains(musica)){

                    estaEmRepertorio = true;

                    break;

                }

            }

            if (!estaEmRepertorio){

                System.out.println(
                        "ID: "
                                + musica.getId()
                                + " | Nome: "
                                + musica.getNome()
                                + " | Artista: "
                                + musica.getArtista()
                );

                encontrou = true;

            }

        }

        if (!encontrou){

            System.out.println(
                    "Todas as músicas estão em repertórios."
            );

        }

    }

    public void editarRepertorio() {

        System.out.println("Digite id: ");

        int id = lerInt();

        sc.nextLine();

        //usa método auxiliar para buscar repertório
        Repertorio repertorioEditar =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        id);

        //verifica se encontrou repertório
        if (repertorioEditar != null) {

            System.out.println("Digite o novo nome ");

            String novoNome = sc.nextLine();

            //edita repertório encontrado
            repertorioEditar.setNome(novoNome);

            System.out.println(
                    "Nome alterado para: "
                            + repertorioEditar.getNome()
            );

        } else {

            System.out.println("Repertório não encontrado.");

        }

    }

    public void exportarRepertorioTxt(){

        System.out.println(
                "Digite o ID do repertório:"
        );

        int id = lerInt();

        Repertorio repertorio =
                repertorioService.buscarRepertorioPorId(
                        repertorios,
                        id);

        if (repertorio != null){

            try {

                FileWriter writer =
                        new FileWriter(
                                repertorio.getNome()
                                        + ".txt"
                        );

                writer.write(
                        "REPERTÓRIO: "
                                + repertorio.getNome()
                                + "\n\n"
                );

                repertorio.ordenarMusicasPorNome();

                int contador = 1;

                for (Musica musica :
                        repertorio.getMusicas()){

                    writer.write(
                            contador
                                    + " - "
                                    + musica.getNome()
                                    + " | "
                                    + musica.getArtista()
                                    + " | Tom: "
                                    + musica.getTom()
                                    + "\n"
                    );

                    contador++;

                }

                writer.close();

                System.out.println(
                        "Repertório exportado!"
                );

            } catch (IOException e){

                System.out.println(
                        "Erro ao exportar repertório."
                );

            }

        } else {

            System.out.println(
                    "Repertório não encontrado!"
            );

        }

    }

    public int gerarProximoIdRepertorio() {

        int maiorId = 0;

        for (Repertorio repertorio : repertorios) {

            if (repertorio.getId() > maiorId) {

                maiorId = repertorio.getId();

            }

        }

        return maiorId + 1;

    }

    public void salvarDados() {

        arquivoService.salvarRepertoriosEmArquivo(
                repertorios
        );

        arquivoService.salvarMusicasEmArquivo(
                musicas
        );

        arquivoService.salvarRelacionamentosEmArquivo(
                repertorios
        );

    }

    //submenu de repertórios
    public void menuRepertorios() {

        //controla submenu repertórios
        boolean executandoRepertorio = true;

        //loop do submenu
        while (executandoRepertorio) {

            //título do menu
            System.out.println("\n===== MENU REPERTÓRIOS =====");

            //opções do submenu
            System.out.println("1 - Listar repertórios");
            System.out.println("2 - Buscar repertório");
            System.out.println("3 - Buscar repertório por nome");
            System.out.println("4 - Adicionar repertório");
            System.out.println("5 - Editar repertório");
            System.out.println("6 - Remover repertório");
            System.out.println("7 - Adicionar música ao repertório");
            System.out.println("8 - Remover música do repertório");
            System.out.println("9 - Ordenar repertórios");
            System.out.println(
                    "10 - Ordenar músicas do repertório"
            );

            System.out.println(
                    "11 - Exportar repertório TXT"
            );

            System.out.println("12 - Voltar");

            //captura opção digitada
            int opcao = lerInt();

            //controle das opções
            switch (opcao) {

                case 1:
                    listarRepertorios();
                    break;

                case 2:
                    buscarRepertorio();
                    break;

                case 3:
                    buscarRepertorioPorNome();
                    break;


                case 4:
                    adicionarRepertorio();
                    break;


                case 5:
                    editarRepertorio();
                    break;


                case 6:
                    removerRepertorio();
                    break;


                case 7:
                    adicionarMusicaExistenteAoRepertorio();
                    break;


                case 8:
                    removerMusicaDoRepertorio();
                    break;


                case 9:
                    ordenarRepertoriosPorNome();
                    break;


                case 10:
                    ordenarMusicasDoRepertorio();
                    break;


                case 11:
                    exportarRepertorioTxt();
                    break;
                case 12:
                    executandoRepertorio = false;
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");

            }

        }

    }

    //submenu de músicas
    public void menuMusicas() {

        //controla submenu músicas
        boolean executandoMusica = true;

        //loop do submenu
        while (executandoMusica) {

            //opções do submenu
            System.out.println("===== MENU MÚSICAS =====");
            System.out.println("1 - Listar músicas");
            System.out.println("2 - Buscar música por ID");
            System.out.println("3 - Buscar música por nome");
            System.out.println("4 - Adicionar música");
            System.out.println("5 - Editar música");
            System.out.println("6 - Remover música");
            System.out.println("7 - Ordenar músicas");
            System.out.println("8 - Listar músicas sem repertório");
            System.out.println("9 - Voltar");

            //captura opção digitada
            int opcao = lerInt();

            //controle das opções
            switch (opcao) {

                case 1:
                    musicaService.listarMusicas(musicas);
                    break;

                case 2:
                    buscarMusica();
                    break;

                case 3:
                    buscarMusicaPorNome();
                    break;

                case 4:
                    adicionarMusica();
                    break;

                case 5:
                    editarMusica();
                    break;

                case 6:
                    removerMusica();
                    break;

                case 7:
                    musicaService.ordenarMusicasPorNome(
                            musicas
                    );
                    break;

                case 8:
                    listarMusicasSemRepertorio();
                    break;

                case 9:
                    executandoMusica = false;
                    System.out.println("Voltando...");
                    break;

            }

        }

    }

    //metodo principal do menu do sistema
    public void iniciarMenu() {

        //limpa listas antes de carregar
        repertorios.clear();

        musicas.clear();

        //carrega os dados iniciais antes do menu iniciar
        arquivoService.carregarRepertoriosDoArquivo(repertorios);

        arquivoService.carregarMusicasDoArquivo(musicas);

        arquivoService.carregarRelacionamentos(
                repertorios,
                musicas
        );

        //controla se o sistema continua aberto ou não
        boolean executando = true;

        //loop principal do sistema
        while (executando) {

            System.out.println(
                    "\nMúsicas cadastradas: "
                            + musicas.size()
            );

            System.out.println(
                    "Repertórios cadastrados: "
                            + repertorios.size()
            );

            System.out.println("1 - Menu repertórios");
            System.out.println("2 - Menu músicas");
            System.out.println("3 - Sair");

            //captura opção digitada pelo usuário
            int opcao = lerInt();

            //estrutura responsável pelas opções do menu
            switch (opcao) {

                case 1:
                    menuRepertorios();
                    break;

                case 2:
                    menuMusicas();
                    break;

                case 3:

                    arquivoService.salvarRepertoriosEmArquivo(repertorios);

                    arquivoService.salvarMusicasEmArquivo(musicas);

                    arquivoService.salvarRelacionamentosEmArquivo(repertorios);

                    sc.close();

                    executando = false;

                    System.out.println("Saindo...");

                    break;

                //executa caso opção não exista
                default:
                    System.out.println("Opção inválida...");
            }

        }

    }

}