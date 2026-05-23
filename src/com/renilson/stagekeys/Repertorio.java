package com.renilson.stagekeys;

import java.util.ArrayList;

public class Repertorio {

        int id;
        String nome;
        ArrayList<Musica>musicas;  //um repertório possui várias músicas.

        //Construtor
        public Repertorio(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.musicas = new ArrayList<>(); //inicializou a lista, sem isso, musica.add(...) daria NullPointerException
        }
        public void adicionarMusica(Musica musica){
            musicas.add(musica);
        }

        public ArrayList<Musica> getMusicas(){
            return musicas;
        }

        public int getId(){
            return id;
        }

        public String getNome(){
            return nome;
        }

        public void setNome(String nome){
            this.nome = nome;
        }


}
