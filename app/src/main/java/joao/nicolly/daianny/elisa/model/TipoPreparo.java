package joao.nicolly.daianny.elisa.model;

public class TipoPreparo {
    private int id;
    private String nome;

    TipoPreparo(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
