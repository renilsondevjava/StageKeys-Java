package com.renilson.stagekeys.service;

import com.renilson.stagekeys.Repertorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RepertorioService {

    public Repertorio buscarRepertorioPorId(
            ArrayList<Repertorio> repertorios,
            int id
    ){

        for (Repertorio repertorio : repertorios){

            if (repertorio.getId() == id){

                return repertorio;

            }

        }

        return null;

    }

    public void ordenarRepertoriosPorNome(
            ArrayList<Repertorio> repertorios
    ){

        Collections.sort(
                repertorios,
                Comparator.comparing(Repertorio::getNome)
        );

    }

    public void listarRepertorios(
            ArrayList<Repertorio> repertorios
    ){

        for (Repertorio repertorio : repertorios){

            System.out.println(
                    "ID: "
                            + repertorio.getId()
                            + " | Nome: "
                            + repertorio.getNome()
                            + " | Músicas: "
                            + repertorio.getMusicas().size()
            );

        }

    }

    public void buscarRepertorioPorNome(
            ArrayList<Repertorio> repertorios,
            String nomeBusca
    ){

        boolean encontrou = false;

        for (Repertorio repertorio : repertorios){

            if (repertorio.getNome()
                    .toLowerCase()
                    .contains(nomeBusca.toLowerCase())){

                System.out.println(
                        "ID: "
                                + repertorio.getId()
                                + " | Nome: "
                                + repertorio.getNome()
                                + " | Músicas: "
                                + repertorio.getMusicas().size()
                );

                encontrou = true;

            }

        }

        if (!encontrou){

            System.out.println(
                    "Nenhum repertório encontrado."
            );

        }

    }

    public int gerarNovoId(
            ArrayList<Repertorio> repertorios
    ){

        int maiorId = 0;

        for (Repertorio repertorio : repertorios){

            if (repertorio.getId() > maiorId){

                maiorId = repertorio.getId();

            }

        }

        return maiorId + 1;

    }

}