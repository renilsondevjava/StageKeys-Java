package com.renilson.stagekeys;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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

                                            listaMusicas
                                                    .getItems()
                                                    .add(
                                                            musica.getNome()
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
        botaoAdicionarMusica.setOnAction(event -> {

            String repertorioSelecionado =
                    lista.getSelectionModel()
                            .getSelectedItem();

            String nomeMusica =
                    campoMusica.getText();

            if (repertorioSelecionado == null) {

                System.out.println(
                        "Selecione um repertório."
                );

                return;

            }

            if (nomeMusica.isBlank()) {

                return;

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
                                "Sem artista",
                                "Sem tom"
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

            }

        });



        VBox layout = new VBox();

        layout.getChildren().add(lista);

        layout.getChildren().add(campoNome);

        layout.getChildren().add(botaoAdicionar);

        layout.getChildren().add(
                campoMusica
        );

        layout.getChildren().add(
                botaoAdicionarMusica
        );

        layout.getChildren().add(
                tituloMusicas
        );

        layout.getChildren().add(
                listaMusicas
        );

        Scene cena = new Scene(layout, 400, 300);

        stage.setTitle("StageKeys");

        stage.setScene(cena);

        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}
