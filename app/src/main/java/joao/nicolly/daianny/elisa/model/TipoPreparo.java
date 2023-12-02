package joao.nicolly.daianny.elisa.model;

public class TipoPreparo {
    private int id_planta;
    String cod_preparo;
    private String nome;
    int idTipoPreparo;

    TipoPreparo(int id_planta,String cod_preparo, String nome, int idTipoPreparo){
        this.id_planta = id_planta;
        this.cod_preparo =cod_preparo;
        this.nome = nome;
        this.idTipoPreparo = idTipoPreparo;
    }

    public int getId() {
        return id_planta;
    }

    public String getCod_preparo() {return cod_preparo;}

    public String getNome() {
        return nome;
    }

    public int getIdTipoPreparo() {return idTipoPreparo;}
}
