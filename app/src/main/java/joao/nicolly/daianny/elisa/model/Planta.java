package joao.nicolly.daianny.elisa.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Planta {

    private String nome;
    private String nome_cientifico;
    private Bitmap imagem;
    private String informacao;
    private ArrayList<Preparo> preparos;
    private ArrayList<Comentario> comentarios;

    public Planta(String nome, String nomeCientifico, Bitmap imagem, String informacao, ArrayList<Preparo> preparos, ArrayList<Comentario> comentarios) {
        this.nome = nome;
        this.nome_cientifico = nomeCientifico;
        this.imagem = imagem;
        this.informacao = informacao;
        this.preparos = preparos;
        this.comentarios = comentarios;
    }

    public String getNome() {
        return nome;
    }

    public String getNome_cientifico() {
        return nome_cientifico;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public String getInformacao() {
        return informacao;
    }

    public ArrayList<Preparo> getPreparos() {
        return preparos;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
}
