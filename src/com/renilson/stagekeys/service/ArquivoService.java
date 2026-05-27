package com.renilson.stagekeys.service;

import com.renilson.stagekeys.Musica;
import com.renilson.stagekeys.Repertorio;

import java.io.*;
import java.util.ArrayList;

public class ArquivoService {

    //salva repertórios em arquivo TXT
    public void salvarRepertoriosEmArquivo(
            ArrayList<Repertorio> repertorios) {

        try {

            FileWriter writer =
                    new FileWriter("repertorios.txt");

            for (Repertorio repertorio : repertorios) {

                writer.write(
                        repertorio.getId()
                                + ";"
                                + repertorio.getNome()
                                + "\n"
                );

            }

            writer.close();

            System.out.println(
                    "Repertórios salvos em arquivo!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar repertórios."
            );

        }

    }

    //carrega repertórios do arquivo TXT
    public void carregarRepertoriosDoArquivo(
            ArrayList<Repertorio> repertorios) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("repertorios.txt")
                    );

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(";");

                int id =
                        Integer.parseInt(dados[0]);

                String nome = dados[1];

                Repertorio repertorio =
                        new Repertorio(id, nome);

                repertorios.add(repertorio);

            }

            reader.close();

            System.out.println(
                    "Repertórios carregados do arquivo!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao carregar repertórios."
            );

        }

    }

    //salva músicas em arquivo TXT
    public void salvarMusicasEmArquivo(
            ArrayList<Musica> musicas) {

        try {

            FileWriter writer =
                    new FileWriter("musicas.txt");

            for (Musica musica : musicas) {

                writer.write(
                        musica.getId()
                                + ";"
                                + musica.getNome()
                                + ";"
                                + musica.getArtista()
                                + ";"
                                + musica.getTom()
                                + "\n"
                );

            }

            writer.close();

            System.out.println(
                    "Músicas salvas em arquivo!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar músicas."
            );

        }

    }

    //carrega músicas do arquivo TXT
    public void carregarMusicasDoArquivo(
            ArrayList<Musica> musicas) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("musicas.txt")
                    );

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(";");

                int id =
                        Integer.parseInt(dados[0]);

                String nome = dados[1];

                String artista = dados[2];

                String tom = dados[3];

                Musica musica =
                        new Musica(
                                id,
                                nome,
                                artista,
                                tom
                        );

                musicas.add(musica);

            }

            reader.close();

            System.out.println(
                    "Músicas carregadas do arquivo!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao carregar músicas."
            );

        }

    }

    public void salvarRelacionamentosEmArquivo(
            ArrayList<Repertorio> repertorios) {

        try {

            FileWriter writer =
                    new FileWriter("relacionamentos.txt");

            for (Repertorio repertorio : repertorios) {

                for (Musica musica : repertorio.getMusicas()) {

                    writer.write(
                            repertorio.getId()
                                    + ";"
                                    + musica.getId()
                                    + "\n"
                    );

                }

            }

            writer.close();

            System.out.println(
                    "Relacionamentos salvos!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar relacionamentos."
            );

        }

    }

    public void carregarRelacionamentos(
            ArrayList<Repertorio> repertorios,
            ArrayList<Musica> musicas) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("relacionamentos.txt")
                    );

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(";");

                int idRepertorio =
                        Integer.parseInt(dados[0]);

                int idMusica =
                        Integer.parseInt(dados[1]);

                Repertorio repertorioEncontrado = null;

                Musica musicaEncontrada = null;

                for (Repertorio repertorio : repertorios) {

                    if (repertorio.getId() == idRepertorio) {

                        repertorioEncontrado = repertorio;

                        break;

                    }

                }

                for (Musica musica : musicas) {

                    if (musica.getId() == idMusica) {

                        musicaEncontrada = musica;

                        break;

                    }

                }

                if (repertorioEncontrado != null
                        && musicaEncontrada != null) {

                    repertorioEncontrado
                            .adicionarMusica(musicaEncontrada);

                }

            }

            reader.close();

            System.out.println(
                    "Relacionamentos carregados!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao carregar relacionamentos."
            );

        }

    }



}