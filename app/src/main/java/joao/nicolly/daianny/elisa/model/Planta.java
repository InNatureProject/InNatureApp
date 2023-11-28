package joao.nicolly.daianny.elisa.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
/**Esta classe armazena as informações referentes ao objeto planta.*/
public class Planta {

    private int id;
    private String nome;
    private String imagem;//TODO: talvez teremos que armazenar uma url, volgo string, ao invés de um bitmap




    public Planta(int id, String nome, String imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getId() {return id;}

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

}
