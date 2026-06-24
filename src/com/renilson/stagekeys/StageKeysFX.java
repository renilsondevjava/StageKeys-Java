package com.renilson.stagekeys;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.layout.HBox;

public class StageKeysFX extends Application {

    @Override
    public void start(Stage stage) {

        Menu menu = new Menu();

        menu.carregarDados();

        ListView<String> lista =
                new ListView<>();

        lista.setPrefHeight(200);

        ListView<String> listaMusicas =
                new ListView<>();

        listaMusicas.setPrefHeight(200);

        Label tituloMusicas =
                new Label(
                        "Músicas do repertório"
                );

        for (Repertorio repertorio :
                menu.getRepertorios()){

            lista.getItems().add(
                    repertorio.getNome()
            );

        }

        Label tituloRepertorios =
                new Label(
                        "REPERTÓRIOS"
                );

        Label tituloCadastroMusica =
                new Label(
                        "CADASTRO DE MÚSICA"
                );

        lista.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, antigo, novo) -> {

                            listaMusicas
                                    .getItems()
                                    .clear();

                            if (novo != null) {

                                for (Repertorio repertorio :
                                        menu.getRepertorios()) {

                                    if (repertorio
                                            .getNome()
                                            .equals(novo)) {

                                        for (Musica musica :
                                                repertorio.getMusicas()) {

                                            listaMusicas.getItems().add(
                                                    musica.getNome()
                                                            + " | "
                                                            + musica.getArtista()
                                                            + " | "
                                                            + musica.getTom()
                                            );

                                        }

                                        break;

                                    }

                                }

                            }

                        }
                );

        TextField campoNome =
                new TextField();

        campoNome.setPromptText(
                "Nome do repertório"
        );

        TextField campoMusica =
                new TextField();

        campoMusica.setPromptText(
                "Nome da música"
        );

        TextField campoArtista =
                new TextField();

        campoArtista.setPromptText(
                "Artista"
        );

        TextField campoTom =
                new TextField();

        campoTom.setPromptText(
                "Tom"
        );

        Button botaoAdicionar =
                new Button("Adicionar");

        botaoAdicionar.setOnAction(event -> {

            String nome =
                    campoNome.getText();

            if (!nome.isBlank()) {

                int novoId =
                        menu.gerarProximoIdRepertorio();

                Repertorio repertorio =
                        new Repertorio(
                                novoId,
                                nome
                        );

                menu.getRepertorios()
                        .add(repertorio);

                menu.salvarDados();

                lista.getItems().add(
                        repertorio.getNome()
                );

                campoNome.clear();

            }

        });

        Button botaoAdicionarMusica =
                new Button(
                        "Adicionar Música"
                );

        Button botaoEditarRepertorio =
                new Button(
                        "Editar Repertório"
                );

        Button botaoRemoverRepertorio =
                new Button(
                        "Remover Repertório"
                );

        Button botaoEditarMusica =
                new Button(
                        "Editar Música"
                );

        Button botaoRemoverMusica =
                new Button(
                        "Remover Música"
                );

        botaoAdicionarMusica.setOnAction(event -> {

            String repertorioSelecionado =
                    lista.getSelectionModel()
                            .getSelectedItem();

            String nomeMusica =
                    campoMusica.getText();

            String artista =
                    campoArtista.getText();

            String tom =
                    campoTom.getText();

            if (repertorioSelecionado == null) {

                System.out.println(
                        "Selecione um repertório."
                );

                return;

            }

            if (nomeMusica.isBlank()) {

                return;

            }

            if (artista.isBlank()) {

                artista = "Sem artista";

            }

            if (tom.isBlank()) {

                tom = "Sem tom";

            }

            int novoId =
                    1;

            for (Musica musica :
                    menu.getMusicas()) {

                if (musica.getId()
                        >= novoId) {

                    novoId =
                            musica.getId() + 1;

                }

            }

            Repertorio repertorioEncontrado = null;

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                if (repertorio.getNome()
                        .equals(repertorioSelecionado)) {

                    repertorioEncontrado =
                            repertorio;

                    break;

                }

            }

            if (repertorioEncontrado != null) {

                Musica novaMusica =
                        new Musica(
                                novoId,
                                nomeMusica,
                                artista,
                                tom
                        );

                menu.getMusicas()
                        .add(novaMusica);

                repertorioEncontrado
                        .adicionarMusica(
                                novaMusica
                        );

                menu.salvarDados();

                listaMusicas
                        .getItems()
                        .add(
                                novaMusica.getNome()
                        );

                campoMusica.clear();
                campoArtista.clear();
                campoTom.clear();

            }

        });

        botaoRemoverMusica.setOnAction(event -> {

            String repertorioSelecionado =
                    lista.getSelectionModel()
                            .getSelectedItem();

            String musicaSelecionada =
                    listaMusicas.getSelectionModel()
                            .getSelectedItem();

            if (repertorioSelecionado == null
                    || musicaSelecionada == null) {

                return;

            }

            Repertorio repertorioEncontrado = null;

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                if (repertorio.getNome()
                        .equals(repertorioSelecionado)) {

                    repertorioEncontrado =
                            repertorio;

                    break;

                }

            }

            Musica musicaEncontrada = null;

            for (Musica musica :
                    repertorioEncontrado.getMusicas()) {

                if (musica.getNome()
                        .equals(musicaSelecionada)) {

                    musicaEncontrada =
                            musica;

                    break;

                }

            }

            if (musicaEncontrada != null) {

                repertorioEncontrado
                        .getMusicas()
                        .remove(
                                musicaEncontrada
                        );

                listaMusicas
                        .getItems()
                        .remove(
                                musicaSelecionada
                        );

                menu.salvarDados();

            }

        });

        botaoEditarRepertorio.setOnAction(event -> {

            String repertorioSelecionado =
                    lista.getSelectionModel()
                            .getSelectedItem();

            String novoNome =
                    campoNome.getText();

            if (repertorioSelecionado == null) {

                return;

            }

            if (novoNome.isBlank()) {

                return;

            }

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                if (repertorio.getNome()
                        .equals(repertorioSelecionado)) {

                    repertorio.setNome(
                            novoNome
                    );

                    break;

                }

            }

            lista.getItems().clear();

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                lista.getItems().add(
                        repertorio.getNome()
                );

            }

            campoNome.clear();

            menu.salvarDados();

        });

        botaoRemoverRepertorio.setOnAction(event -> {

            String repertorioSelecionado =
                    lista.getSelectionModel()
                            .getSelectedItem();

            if (repertorioSelecionado == null) {

                return;

            }

            Repertorio repertorioEncontrado = null;

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                if (repertorio.getNome()
                        .equals(repertorioSelecionado)) {

                    repertorioEncontrado =
                            repertorio;

                    break;

                }

            }

            if (repertorioEncontrado != null) {

                ArrayList<Musica> musicasParaRemover =
                        new ArrayList<>();

                for (Musica musica :
                        repertorioEncontrado.getMusicas()) {

                    if (!menu.musicaEstaEmOutroRepertorio(
                            musica,
                            repertorioEncontrado)) {

                        musicasParaRemover.add(musica);

                    }

                }

                menu.getMusicas()
                        .removeAll(
                                musicasParaRemover
                        );

                menu.getRepertorios()
                        .remove(
                                repertorioEncontrado
                        );

                lista.getItems()
                        .remove(
                                repertorioSelecionado
                        );

                listaMusicas
                        .getItems()
                        .clear();

                menu.salvarDados();

            }


        });

        botaoEditarMusica.setOnAction(event -> {

            String repertorioSelecionado =
                    lista.getSelectionModel()
                            .getSelectedItem();

            String musicaSelecionada =
                    listaMusicas.getSelectionModel()
                            .getSelectedItem();

            String novoNome =
                    campoMusica.getText();

            String novoArtista =
                    campoArtista.getText();

            String novoTom =
                    campoTom.getText();


            if (repertorioSelecionado == null
                    || musicaSelecionada == null) {

                return;

            }

            if (novoNome.isBlank()) {

                return;

            }

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                if (repertorio.getNome()
                        .equals(repertorioSelecionado)) {

                    for (Musica musica :
                            repertorio.getMusicas()) {

                        if (musicaSelecionada.startsWith(
                                musica.getNome()
                        )) {

                            musica.setNome(
                                    novoNome
                            );

                            musica.setArtista(
                                    novoArtista
                            );

                            musica.setTom(
                                    novoTom
                            );


                            break;

                        }

                    }

                    break;

                }

            }

            listaMusicas.getItems().clear();

            for (Repertorio repertorio :
                    menu.getRepertorios()) {

                if (repertorio.getNome()
                        .equals(repertorioSelecionado)) {

                    for (Musica musica :
                            repertorio.getMusicas()) {

                        listaMusicas.getItems().add(
                                musica.getNome()
                                        + " | "
                                        + musica.getArtista()
                                        + " | "
                                        + musica.getTom()
                        );

                    }

                    break;

                }

            }

            campoMusica.clear();

            menu.salvarDados();

        });


        VBox layout = new VBox();

        HBox botoesRepertorio =
                new HBox(10);

        botoesRepertorio.getChildren().addAll(
                botaoAdicionar,
                botaoRemoverRepertorio,
                botaoEditarRepertorio
        );

        HBox botoesMusica =
                new HBox(10);

        botoesMusica.getChildren().addAll(
                botaoAdicionarMusica,
                botaoRemoverMusica,
                botaoEditarMusica
        );

        layout.getChildren().add(
                tituloRepertorios
        );

        layout.setSpacing(10);

        layout.getChildren().add(lista);

        layout.getChildren().add(campoNome);


        layout.getChildren().add(
                botoesRepertorio
        );

        layout.getChildren().add(
                tituloCadastroMusica
        );

        layout.getChildren().add(
                campoMusica
        );

        layout.getChildren().add(
                campoArtista
        );

        layout.getChildren().add(
                campoTom
        );

        layout.getChildren().add(
                botoesMusica
        );


        layout.getChildren().add(
                tituloMusicas
        );

        layout.getChildren().add(
                listaMusicas
        );

        Scene cena = new Scene(layout, 700, 600);

        stage.setTitle("StageKeys");

        stage.setScene(cena);

        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}
