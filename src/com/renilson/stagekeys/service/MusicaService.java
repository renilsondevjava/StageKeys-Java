package com.renilson.stagekeys.service;

import com.renilson.stagekeys.Musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MusicaService {

    //lista músicas
    public void listarMusicas(
            ArrayList<Musica> musicas){

        if (musicas.isEmpty()){

            System.out.println(
                    "Nenhuma música cadastrada."
            );

            return;

        }

        for (Musica musica : musicas){

            System.out.println(
                    "ID: " + musica.getId()
                            + " | Nome: "
                            + musica.getNome()
                            + " | Artista: "
                            + musica.getArtista()
                            + " | Tom: "
                            + musica.getTom()
            );

        }

    }

    //busca música por ID
    public Musica buscarMusicaPorId(
            ArrayList<Musica> musicas,
            int id){

        for (Musica musica : musicas){

            if (musica.getId() == id){

                return musica;

            }

        }

        return null;

    }

    //ordena músicas
    public void ordenarMusicasPorNome(
            ArrayList<Musica> musicas){

        Collections.sort(
                musicas,
                Comparator.comparing(
                        Musica::getNome
                )
        );

        System.out.println(
                "Músicas ordenadas!"
        );

    }

    public int gerarNovoId(
            ArrayList<Musica> musicas
    ){

        int maiorId = 0;

        for (Musica musica : musicas){

            if (musica.getId() > maiorId){

                maiorId = musica.getId();

            }

        }

        return maiorId + 1;

    }

}