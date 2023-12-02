package joao.nicolly.daianny.elisa.model;

public class TipoPreparo {
    private int id_planta;
    String cod_preparo;
    private String nome;

    TipoPreparo(int id_planta,String cod_preparo, String nome){
        this.id_planta = id_planta;
        this.cod_preparo =cod_preparo;
        this.nome = nome;
    }

    public int getId() {
        return id_planta;
    }

    public String getCod_preparo() {
        return cod_preparo;
    }

    public String getNome() {
        return nome;
    }
}
