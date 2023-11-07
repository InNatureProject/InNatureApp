package joao.nicolly.daianny.elisa.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import joao.nicolly.daianny.elisa.util.Config;
import joao.nicolly.daianny.elisa.util.HttpRequest;
import joao.nicolly.daianny.elisa.util.Util;

/**
 * Essa classe concentra todos os métodos de conexão entre a app e o servidor web
 */

/***/
public class InNatureRepository {

    //Variaveis
    Context context;
    public InNatureRepository(Context context) {this.context = context; }

    public boolean cadastrar(String nome, String email, String senha) {

        //Requisição HTTP com parametros pro servidor

        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL + "api/users/cadastro", "POST", "UTF-8");
        /** Adição as tabelas
         *      Os informações obtidas serão */
        httpRequest.addParam("nome", nome);
        httpRequest.addParam("email", email);
        httpRequest.addParam("senha", senha);

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
                //TODO: Ver se a mensagem que retorna quando o cadastro é efetiVADO REALMENTE É SUCESSO = 1
                //TODO: Nesta parte pegar o tolking e setá-lo na app, pois é oque usaremos para comentar e favoritar.
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


    /**
     *Este método será usado para puxar do Banco de Dados uma quantidae determinada, pelo loadSize, de plantas.
     * O método para carregar os comentários será semelhante.
     * Para visualizar as plantas tanto quanto para visualizar comentários não será necessário login.*/


    //TODO: Fazer Função loadPlanta
    public List<Planta> loadPlanta(Integer limit, Integer offSet) {

        /**Não é necessário validação de usuário (login, senha ou tolking) para carregar as plantas*/

        // cria a lista de produtos incicialmente vazia, que será retornada como resultado
        List<Planta> plantaList = new ArrayList<>();

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"api/command/falta parte do comando", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();//Este erro é devido a falta do catch
            //resultado provavelmente será em uma string de formato JSON que preciso perguntar ao João como virá
            result = Util.inputStream2String(is,"UTF-8");//Este erro é devido a falta do catch

            //fechando conecção com servidor
            httpRequest.finish();//Este erro é devido a falta do catch

            Log.i("HTTP RESULTADO   DA REQUISIÇÃO",result);
        }//Este erro é devido a falta do catch


    } //este erro é devido a falta de return
}
