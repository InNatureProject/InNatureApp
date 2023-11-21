package joao.nicolly.daianny.elisa.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
/**Esta classe armazena as informações referentes ao objeto planta.*/
public class Planta {

    private int id;
    private String nome;
    private String nome_cientifico;
    private Bitmap imagem;
    private String informacao;
    private ArrayList<TipoPreparo> preparos;
    private ArrayList<Comentario> comentarios;


    public Planta(int id, String nome, String nomeCientifico, Bitmap imagem, String informacao, ArrayList<TipoPreparo> preparos, ArrayList<Comentario> comentarios) {
        this.id = id;
        this.nome = nome;
        this.nome_cientifico = nomeCientifico;
        this.imagem = imagem;
        this.informacao = informacao;
        this.preparos = preparos;
        this.comentarios = comentarios;
    }

    public int getId() {return id;}

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

    public ArrayList<TipoPreparo> getPreparos() {
        return preparos;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
}
