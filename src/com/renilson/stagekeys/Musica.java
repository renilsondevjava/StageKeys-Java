package com.renilson.stagekeys;

// Classe que representa uma música do sistema
public class Musica {

    // Atributos da música
    private int id;
    private String nome;
    private String artista;
    private String tom;

    // Construtor:
    // usado para criar objetos já preenchidos
    public Musica(int id, String nome, String artista, String tom) {

        // "this" referencia os atributos da própria classe
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.tom = tom;
    }

    // Getter do ID, nome, artista, tom:
    // retorna o id, nome, artista e o tom da música
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public String getTom() {
        return tom;
    }

    //métodos setters altera informação de nome, artista, tom
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setTom(String tom) {
        this.tom = tom;
    }

}
//📌 Atributos => Guardam os dados do objeto.

//📌 Construtor => Cria o objeto já preenchido.

//📌 Getters => Permitem acessar os dados do objeto.

