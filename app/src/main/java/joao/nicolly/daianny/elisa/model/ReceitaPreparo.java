package joao.nicolly.daianny.elisa.model;

import org.json.JSONArray;

import java.util.ArrayList;

public class ReceitaPreparo {

    private String titulo;
    private String receita;
    private JSONArray indicacao;
    private JSONArray contraindicação;
    private JSONArray efeitoColateral;

    public ReceitaPreparo(String titulo, String receita,JSONArray indicacao,JSONArray contraindicação,JSONArray efeitoColateral){
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

    public JSONArray getIndicacao() {
        return indicacao;
    }

    public JSONArray getContraindicação() {
        return contraindicação;
    }

    public JSONArray getEfeitoColateral() {
        return efeitoColateral;
    }
}
