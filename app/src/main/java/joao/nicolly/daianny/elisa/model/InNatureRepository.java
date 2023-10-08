package joao.nicolly.daianny.elisa.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import joao.nicolly.daianny.elisa.util.Config;
import joao.nicolly.daianny.elisa.util.HttpRequest;
import joao.nicolly.daianny.elisa.util.Util;

/**
 * Essa classe concentra todos os métodos de conexão entre a app e o servidor web
 */
public class InNatureRepository {

    //Variaveis
    Context context;
    public InNatureRepository(Context context) {this.context = context; }

    public boolean cadastrar(String nome, String email, String senha) {

        //Requisição HTTP com parametros pro servidor

        /*TODO  se não me engano a string doparametro tem que ser o nome da coluna em que tais parametros serão armazenados
            checar se estou correta
            descobrir o nomedas colunas caso esteja cera ou
            descobrir o que tem que vir aqui
        */
        HttpRequest httpRequest = new HttpRequest(Config.PRODUCTS_APP_URL + "função desejada", "POST", "UTF-8");
        httpRequest.addParam("Nome da coluna", nome);
        httpRequest.addParam("Nome da coluna", email);
        httpRequest.addParam("Nome da coluna", senha);

        String result = "";

        try{
            //executa a requisição http
            InputStream is = httpRequest.execute();

            //Os dados chegam em uma string com formato json
            result = Util.inputStream2String(is,"UTF-8");
            Log.i("HTTP RESULTADO DO CADASTRO",result);

            //fim da conexão
            httpRequest.finish();

            // o resultado é transformado em objeto json que pode ser utilizado
            JSONObject jsonObject = new JSONObject(result);

            int sucesso = jsonObject.getInt("sucesso");

            if(sucesso == 1){
                return true;
            }
        } catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
            Log.e("Resultado HTTP",result);
        }
        return false;
    }
}
