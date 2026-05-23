package com.renilson.stagekeys;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Menu {

    //Scanner usado para capturar dados digitados pelo usuário
    Scanner sc = new Scanner(System.in);

    //lista geral de músicas do sistema
    ArrayList<Musica> musicas = new ArrayList<>();

    //lista geral de repertorios do sistema
    ArrayList<Repertorio> repertorios = new ArrayList<>();

    //metodo responsável por carregar dados iniciais do sistema
    public void carregarDados() {

        // Criação das músicas
        Musica m1 = new Musica(
                1,
                "A Casa",
                "Toque no Altar",
                "D"
        );

        Musica m2 = new Musica(
                2,
                "Raridade",
                "Anderson Freire",
                "G"
        );

        Musica m3 = new Musica(
                3,
                "Tú és Santo",
                "Walmir Alencar",
                "D"
        );

        // Criação dos repertórios
        Repertorio r1 = new Repertorio(
                1,
                "Forró do GM"
        );

        Repertorio r2 = new Repertorio(
                2,
                "Marcinho Moral"
        );

        // adiciona músicas na lista geral
        musicas.add(m1);
        musicas.add(m2);
        musicas.add(m3);

        // adiciona repertórios na lista geral
        repertorios.add(r1);
        repertorios.add(r2);

        // adiciona músicas dentro dos repertórios
        r1.adicionarMusica(m1);
        r1.adicionarMusica(m2);
        r1.adicionarMusica(m3);

        r2.adicionarMusica(m2);

    }

    //lista todos os repertórios cadastrados
    public void listarRepertorios() {
        //percorre lista de repertórios
        for (Repertorio repertorio : repertorios) {

            //exibe id e nome do repertório
            System.out.println(
                    "ID: " + repertorio.getId()
                            + " | Nome: " + repertorio.getNome());
        }
    }

    //busca repertório pelo ID e lista músicas dele
    public void buscarRepertorio() {
        //pede ID do repertório
        System.out.println("Digite o id do repertório: ");

        int idRepertorio = lerInt();

        //método auxiliar responsável por buscar repertório pelo ID
        Repertorio repertorio
                = buscarRepertorioPorId(idRepertorio);

        //exibe repertório encontrado
        System.out.println(
                "Encontrado: " + repertorio.getNome()
        );

        //percorre músicas do repertório encontrado
        for (Musica musica : repertorio.getMusicas()) {

            //exibe atributos da música
            System.out.println(
                    "Música: " + musica.getNome() +
                            " | Artista: " + musica.getArtista() +
                            " | Tom: " + musica.getTom()
            );

        }

    }

    //adiciona novo repertório
    public void adicionarRepertorio() {
        //pede ID do repertório
        System.out.println("Digite o ID do repertório:");

        int novoId = lerInt();

        //verifica se ID já existe
        Repertorio repertorioExistente =
                buscarRepertorioPorId(novoId);

//se encontrou repertório com mesmo ID
        if (repertorioExistente != null) {

            System.out.println(
                    "Já existe um repertório com esse ID!"
            );

            return;

        }

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
                = buscarRepertorioPorId(idRepertorioMusica);

        // verifica se encontrou algum repertório
        if (repertorioEncontrado != null) {

            // pede o ID da nova música
            System.out.println("Digite o ID da música:");
            int idMusica = lerInt();

            //verifica se já existe música com mesmo ID
            Musica musicaExistente =
                    buscarMusicaPorId(idMusica);

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

            // adiciona música dentro do repertório encontrado
            repertorioEncontrado.adicionarMusica(novaMusica);

            //mensagem de sucesso
            System.out.println("Música adicionada com sucesso!");

        } else {
            // executa se nenhum repertório for encontrado
            System.out.println("Repertório não encontrado!");

        }
    }

    //metodo para remover musica
    public void removerMusica() {
        System.out.println("Digite o id da musica: ");
        int id = lerInt();

        //usa método auxiliar para buscar musica
        Musica musicaRemover =
                buscarMusicaPorId(id);

        //verifica se encontrou musica
        if (musicaRemover != null) {

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
                buscarMusicaPorId(idMusica);

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

    public void editarMusica() {
        //pede ID da música
        System.out.println("Digite o id da musica: ");

        int idMusica = lerInt();

        //libera o scanner
        sc.nextLine();

        Musica musicaEditar =
                buscarMusicaPorId(idMusica);

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

    //método auxiliar responsável por buscar repertório pelo ID
    public Repertorio buscarRepertorioPorId(int id) {

        //percorre lista de repertórios
        for (Repertorio repertorio : repertorios) {

            //verifica se encontrou o ID
            if (repertorio.getId() == id) {

                //retorna o objeto encontrado
                return repertorio;

            }

        }

        //retorna null se não encontrar repertório
        return null;

    }

    //método auxiliar responsável por buscar música pelo ID
    public Musica buscarMusicaPorId(int id) {
        //percorre lista de músicas
        for (Musica musica : musicas) {

            //verifica se encontrou o ID
            if (musica.getId() == id) {

                //retorna música encontrada
                return musica;

            }

        }

        //retorna null se não encontrar música
        return null;
    }

    //remover repertorio
    public void removerRepertorio() {
        System.out.println("Digite o id do repertorio: ");
        int id = lerInt();

        //usa método auxiliar para buscar repertório
        Repertorio repertorioRemover =
                buscarRepertorioPorId(id);

        //verifica se encontrou repertório
        if (repertorioRemover != null) {

            //remove repertório da lista
            repertorios.remove(repertorioRemover);

            System.out.println("Repertório " + repertorioRemover.getNome() +
                    " removido com sucesso!");
        } else {
            System.out.println("Repertório não encontrado.");
        }

    }

    public void editarRepertorio() {

        System.out.println("Digite id: ");

        int id = lerInt();

        sc.nextLine();

        //usa método auxiliar para buscar repertório
        Repertorio repertorioEditar =
                buscarRepertorioPorId(id);

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

    //salva repertórios em arquivo TXT
    public void salvarRepertoriosEmArquivo() {

        try {

            //cria arquivo para escrita
            FileWriter writer =
                    new FileWriter("repertorios.txt");

            //percorre lista de repertórios
            for (Repertorio repertorio : repertorios) {

                //escreve dados do repertório no arquivo
                writer.write(
                        repertorio.getId()
                                + " - "
                                + repertorio.getNome()
                                + "\n"
                );

            }

            //fecha arquivo
            writer.close();

            System.out.println(
                    "Repertórios salvos em arquivo!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar arquivo."
            );

        }

    }

    //salva músicas em arquivo TXT
    public void salvarMusicasEmArquivo(){

        try {

            //cria arquivo para escrita
            FileWriter writer =
                    new FileWriter("musicas.txt");

            //percorre lista de músicas
            for (Musica musica : musicas){

                //escreve dados da música no arquivo
                writer.write(
                        musica.getId()
                                + " - "
                                + musica.getNome()
                                + " - "
                                + musica.getArtista()
                                + " - "
                                + musica.getTom()
                                + "\n"
                );

            }

            //fecha arquivo
            writer.close();

            System.out.println(
                    "Músicas salvas em arquivo!"
            );

        } catch (IOException e){

            System.out.println(
                    "Erro ao salvar músicas."
            );

        }

    }

    //salva relacionamento entre repertórios e músicas
    public void salvarRelacionamentos(){

        try {

            //cria arquivo para escrita
            FileWriter writer =
                    new FileWriter("relacionamentos.txt");

            //percorre repertórios
            for (Repertorio repertorio : repertorios){

                //percorre músicas do repertório
                for (Musica musica : repertorio.getMusicas()){

                    //salva relação repertório -> música
                    writer.write(
                            repertorio.getId()
                                    + " - "
                                    + musica.getId()
                                    + "\n"
                    );

                }

            }

            //fecha arquivo
            writer.close();

            System.out.println(
                    "Relacionamentos salvos!"
            );

        } catch (IOException e){

            System.out.println(
                    "Erro ao salvar relacionamentos."
            );

        }

    }

    //carrega repertórios do arquivo TXT
    public void carregarRepertoriosDoArquivo(){

        try {

            //abre arquivo para leitura
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("repertorios.txt")
                    );

            //variável para armazenar linha atual
            String linha;

            //lê linha por linha até acabar arquivo
            while ((linha = reader.readLine()) != null){

                //separa ID e nome usando " - "
                String[] partes = linha.split(" - ");

                //converte texto para número
                int id = Integer.parseInt(partes[0]);

                //pega nome do repertório
                String nome = partes[1];

                //cria objeto repertório
                Repertorio repertorio =
                        new Repertorio(id, nome);

                //adiciona na lista
                repertorios.add(repertorio);

            }

            //fecha arquivo
            reader.close();

            System.out.println(
                    "Repertórios carregados do arquivo!"
            );

        } catch (IOException e){

            System.out.println(
                    "Erro ao carregar arquivo."
            );

        }

    }

    //carrega músicas do arquivo TXT
    public void carregarMusicasDoArquivo(){

        try {

            //abre arquivo para leitura
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("musicas.txt")
                    );

            //variável para armazenar linha atual
            String linha;

            //lê linha por linha até acabar arquivo
            while ((linha = reader.readLine()) != null){

                //separa dados usando " - "
                String[] partes = linha.split(" - ");

                //converte ID para número
                int id = Integer.parseInt(partes[0]);

                //pega dados da música
                String nome = partes[1];
                String artista = partes[2];
                String tom = partes[3];

                //cria objeto música
                Musica musica =
                        new Musica(
                                id,
                                nome,
                                artista,
                                tom
                        );

                //adiciona música na lista
                musicas.add(musica);

            }

            //fecha arquivo
            reader.close();

            System.out.println(
                    "Músicas carregadas do arquivo!"
            );

        } catch (IOException e){

            System.out.println(
                    "Erro ao carregar músicas."
            );

        }

    }

    //carrega relacionamentos entre repertórios e músicas
    public void carregarRelacionamentos(){

        try {

            //abre arquivo para leitura
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("relacionamentos.txt")
                    );

            //variável para armazenar linha atual
            String linha;

            //lê linha por linha
            while ((linha = reader.readLine()) != null){

                //separa IDs
                String[] partes = linha.split(" - ");

                //converte IDs para número
                int idRepertorio =
                        Integer.parseInt(partes[0]);

                int idMusica =
                        Integer.parseInt(partes[1]);

                //busca objetos pelos IDs
                Repertorio repertorio =
                        buscarRepertorioPorId(idRepertorio);

                Musica musica =
                        buscarMusicaPorId(idMusica);

                //verifica se encontrou os dois objetos
                if (repertorio != null && musica != null){

                    //recria relacionamento
                    repertorio.adicionarMusica(musica);

                }

            }

            //fecha arquivo
            reader.close();

            System.out.println(
                    "Relacionamentos carregados!"
            );

        } catch (IOException e){

            System.out.println(
                    "Erro ao carregar relacionamentos."
            );

        }

    }

    //submenu de repertórios
    public void menuRepertorios() {

        //controla submenu repertórios
        boolean executandoRepertorio = true;

        //loop do submenu
        while (executandoRepertorio) {

            //opções do submenu
            System.out.println("===== MENU REPERTÓRIOS =====");
            System.out.println("1 - Listar repertórios");
            System.out.println("2 - Buscar repertório");
            System.out.println("3 - Adicionar repertório");
            System.out.println("4 - Editar repertório");
            System.out.println("5 - Remover repertório");
            System.out.println("6 - Voltar");

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
                    adicionarRepertorio();
                    break;

                case 4:
                    editarRepertorio();
                    break;

                case 5:
                    removerRepertorio();
                    break;

                case 6:
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
            System.out.println("1 - Buscar música");
            System.out.println("2 - Adicionar música");
            System.out.println("3 - Editar música");
            System.out.println("4 - Remover música");
            System.out.println("5 - Voltar");

            //captura opção digitada
            int opcao = lerInt();

            //controle das opções
            switch (opcao) {

                case 1:
                    buscarMusica();
                    break;

                case 2:
                    adicionarMusica();
                    break;

                case 3:
                    editarMusica();
                    break;

                case 4:
                    removerMusica();
                    break;

                case 5:
                    executandoMusica = false;
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");

            }

        }

    }

    //metodo principal do menu do sistema
    public void iniciarMenu() {

        //limpa listas antes de carregar
        repertorios.clear();

        musicas.clear();

        //carrega os dados iniciais antes do menu iniciar
        carregarRepertoriosDoArquivo();

        carregarMusicasDoArquivo();

        carregarRelacionamentos();

        //controla se o sistema continua aberto ou não
        boolean executando = true;

        //loop principal do sistema
        while (executando) {

            //exibe opções do menu
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

                    salvarRepertoriosEmArquivo();

                    salvarMusicasEmArquivo();

                    salvarRelacionamentos();

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