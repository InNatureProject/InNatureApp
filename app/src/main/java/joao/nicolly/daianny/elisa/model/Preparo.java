package joao.nicolly.daianny.elisa.model;

import java.util.ArrayList;

public class Preparo {
    private String nome;
    private ArrayList<String> ingredientes;
    private ArrayList<String> passos;
    private String criador;

    public Preparo(String nome, ArrayList<String> ingredientes, ArrayList<String> passos, String criador) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.passos = passos;
        this.criador = criador;
    }
}
