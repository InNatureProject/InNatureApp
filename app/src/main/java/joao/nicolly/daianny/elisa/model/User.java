package joao.nicolly.daianny.elisa.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class User {

    private Bitmap fotoDePerfil;
    private String nomeDeUsuario;
    private String emailDeUsuario;
    private ArrayList<Preparo> preparosFavoritados;

    public User(Bitmap fotoDePerfil, String nomeDeUsuario, String emailDeUsuario, ArrayList<Preparo> preparosFavoritados) {
        this.fotoDePerfil = fotoDePerfil;
        this.nomeDeUsuario = nomeDeUsuario;
        this.emailDeUsuario = emailDeUsuario;
        this.preparosFavoritados = preparosFavoritados;
    }
}
