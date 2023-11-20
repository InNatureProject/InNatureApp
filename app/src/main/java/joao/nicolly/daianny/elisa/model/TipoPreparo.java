package joao.nicolly.daianny.elisa.model;

public class TipoPreparo {
    private int id;
    private String text;

    public TipoPreparo(int id, String text){
        this.id = id;
        this.text = text;
    }



    public int getId() {
        return id;
    }


    public String getText() {
        return text;
    }
}
