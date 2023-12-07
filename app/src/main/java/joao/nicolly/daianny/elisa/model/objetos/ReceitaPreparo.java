package joao.nicolly.daianny.elisa.model.objetos;

import org.json.JSONArray;

import java.util.ArrayList;

public class ReceitaPreparo {

    private String titulo;
    private String receita;
    private ArrayList<String> indicacao;
    private ArrayList<String> contraindicação;
    private ArrayList<String> efeitoColateral;

    public ReceitaPreparo(String titulo, String receita,ArrayList<String> indicacao,ArrayList<String> contraindicação,ArrayList<String> efeitoColateral){
        this.titulo = titulo;
        this.receita = receita;
        this.indicacao = indicacao;
        this.contraindicação = contraindicação;
        this.efeitoColateral = efeitoColateral;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getReceita() {
        return receita;
    }

    public ArrayList<String> getIndicacao() {
        return indicacao;
    }

    public ArrayList<String> getContraindicação() {
        return contraindicação;
    }

    public ArrayList<String> getEfeitoColateral() {
        return efeitoColateral;
    }
}
