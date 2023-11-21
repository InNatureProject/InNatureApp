package joao.nicolly.daianny.elisa.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
/**Esta classe armazena as informações referentes a um Usuário.
 * Provavelmente será apagada pois as informações estão sendo armazenadas em ul local mais seguro.
 * Lembrando que só falta armazenar a imagem do usuário pois o nome, o email e a senha já estão sendo armazenados assim como um tolking*/
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
