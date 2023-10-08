package joao.nicolly.daianny.elisa.model;

import android.app.Application;

/**
 * Essa classe concentra todos os métodos de conexão entre a app e o servidor web
 */
public class InNatureRepository {
    public <T extends Application> InNatureRepository(T application) {
        //TODO checar se está certo esta estrutura e terminá-la
    }

    public boolean cadastrar(String nome, String email, String senha) {
        return true;//TODO fazer método cadastro
    }
}
