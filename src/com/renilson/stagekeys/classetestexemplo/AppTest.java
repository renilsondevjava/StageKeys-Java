package com.renilson.stagekeys.classetestexemplo;

import com.renilson.stagekeys.Musica;
import com.renilson.stagekeys.Repertorio;

import java.util.ArrayList;
import java.util.Scanner;

public class AppTest {
    // Metodo principal: é o primeiro metodo executado quando o programa inicia
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Musica> musicas = new ArrayList<>(); //Arraylist
        ArrayList<Repertorio> repertorios = new ArrayList<>();
        // Criação de um objeto da classe Musica, "m1" é a variável que guarda o objeto criado.
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
        Repertorio r1 = new Repertorio( // cria lista de repertórios
                1,
                "Forró do GM"
        );
        Repertorio r2 = new Repertorio(
                2,
                "Marcinho Moral"
        );
        //adiciona objetos do tipo Musica
        musicas.add(m1);
        musicas.add(m2);
        musicas.add(m3);

        //adiciona objetos do Repertorio
        repertorios.add(r1);
        repertorios.add(r2);

        //adiciona musicas dentro do repertorio
        r1.adicionarMusica(m1);
        r1.adicionarMusica(m2);
        r1.adicionarMusica(m3);
        r2.adicionarMusica(m2);



        // metodo setter sendo aplicado altera o tom da música, nome, artista
        // m1.setTom("E");
        // m1.setNome("nome alterado");
        // m1.setArtista("artista alterado");


        //metodo serve pra listar e exibir as musicas do repertorio
        for (Musica musica : r1.getMusicas()) {

            System.out.println(
                    musica.getNome() +
                            " - " +
                            musica.getArtista() +
                            " - Tom: " +
                            musica.getTom()
            );

        }
        //metodo para buscar repertório pelo id
        int idRepertorio = 2;
        for (Repertorio repertorio : repertorios){
            if (repertorio.getId() == idRepertorio){
                System.out.println("Encontrado: "+repertorio.getNome());
            }
        }

        //metodo para listar os repertórios
        for (Repertorio repertorio : repertorios){ // Objeto, variável temporária, : lista que será percorrida
            System.out.println("ID: "+repertorio.getId()+" | Nome: "+repertorio.getNome()); //exibe os itens da lista com os atributos que for solicitado
        }

        //metodo para alterar editar nome do repertório buscando pelo id
        String novoNome = "Novo Nome";
        for (Repertorio repertorio : repertorios){// procura o repertorio

            if (repertorio.getId() == idRepertorio){ //acha o repertorio do id escolhido

                repertorio.setNome(novoNome);//edita o objeto encontrado

                System.out.println("Nome alterado para: " + repertorio.getNome());

            }
        }

        //metodo para remover repertorio
        Repertorio repertorioRemover = null; // cria variável vazia e Guarda temporariamente o repertório encontrado para remover depois do loop

        for (Repertorio repertorio : repertorios){ //procura o repertorio

            if (repertorio.getId() == idRepertorio){ //acha pelo seu id

                repertorioRemover = repertorio; //guarda o repertória na variável repertorioRemover

            }
        }

        if (repertorioRemover != null){ //ou seja se o repertório tiver algum valor que nao seja nulo faça...

            repertorios.remove(repertorioRemover); //repertorio é removido

            System.out.println("Repertório removido!");

        }

        //inicio do CRUD da classe Musica
        //metodo de busca por id
        int idMusica =1;
        for (Musica musica : musicas){ //percorre a lista musicas
            if(musica.getId() == idMusica){ // acha pelo seu id
                System.out.println(musica.getNome());
            }
        }

        //metodo para alterar editar nome das musicas, artista e tom das musicas pelo id
        String novoNomeMusica = "Forro Saborear"; //novo valor depois de alterado
        String novoNomeArtista = "Benedito cantor"; //novo valor depois de alterado
        String novoTom = "F"; //novo valor depois de alterado

        for (Musica musica : musicas){ //percorre a lista

            if (musica.getId() == idMusica){ //encontrou pelo ID

                musica.setNome(novoNomeMusica); //alterou o objeto
                musica.setArtista(novoNomeArtista); //alterou o objeto
                musica.setTom(novoTom); //alterou o objeto

                System.out.println("Nome alterado: " + musica.getNome());
                System.out.println("Artista alterado: " + musica.getArtista());
                System.out.println("Tom alterado: " + musica.getTom());

            }
        }
        //metodo para remover musica
        Musica musicaRemover = null; //cria variável vazia e Guarda temporariamente a musica encontrado para remover depois do loop

        for (Musica musica : musicas){ //percorre a lista

            if (musica.getId() == idMusica){ //acha pelo seu id

                musicaRemover = musica; //guarda a musica na variável musicarRemover
            }
        }
        if (musicaRemover != null){ //ou seja se a musica tiver algum valor que nao seja nulo faça...

            musicas.remove(musicaRemover); //remove a musica

            System.out.println("Música removida");
        }

    }
}

