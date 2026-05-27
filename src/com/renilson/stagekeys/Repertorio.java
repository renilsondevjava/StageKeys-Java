package com.renilson.stagekeys;

import java.util.ArrayList;

public class Repertorio {

    private int id;
    private String nome;
    private ArrayList<Musica> musicas;  //um repertório possui várias músicas.

    //Construtor
    public Repertorio(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.musicas = new ArrayList<>(); //inicializou a lista, sem isso, musica.add(...) daria NullPointerException
    }

    public boolean adicionarMusica(Musica musica){

        for (Musica m : musicas){

            if (m.getId() == musica.getId()){

                return false;

            }

        }

        musicas.add(musica);

        return true;

    }

    public boolean removerMusica(int idMusica){

        for (int i = 0; i < musicas.size(); i++){

            if (musicas.get(i).getId() == idMusica){

                musicas.remove(i);

                return true;

            }

        }

        return false;

    }

    public void ordenarMusicasPorNome(){

        musicas.sort(
                (m1, m2) ->
                        m1.getNome()
                                .compareToIgnoreCase(
                                        m2.getNome()
                                )
        );

    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
