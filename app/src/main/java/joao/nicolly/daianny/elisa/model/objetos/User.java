package joao.nicolly.daianny.elisa.model.objetos;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class User {

    private Bitmap fotoDePerfil;
    private String nomeDeUsuario;
    private String emailDeUsuario;
    private ArrayList<Planta> plantasFavoritados;

    public User(Bitmap fotoDePerfil, String nomeDeUsuario, String emailDeUsuario, ArrayList<Planta> plantasFavoritados) {
        this.fotoDePerfil = fotoDePerfil;
        this.nomeDeUsuario = nomeDeUsuario;
        this.emailDeUsuario = emailDeUsuario;
        this.plantasFavoritados = plantasFavoritados;
    }
}
