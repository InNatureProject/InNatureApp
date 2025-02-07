package joao.nicolly.daianny.elisa.model.objetos;

import androidx.lifecycle.Observer;

import joao.nicolly.daianny.elisa.activity.PlantaActivity;

/**Esta classe armazena as informações referentes ao objeto planta.*/
public class Planta {

    private int id;
    private String nome;
    private String imagem;
    private String desc;




    public Planta(int id, String nome, String imagem, String desc) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.desc = desc;
    }

    public static void observe(PlantaActivity plantaActivity, Observer<Planta> plantaObserver) {
    }

    public int getId() {return id;}

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public String getDesc() {return desc;}
}
