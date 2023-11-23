package joao.nicolly.daianny.elisa.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.GenericArrayType;
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

        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL + "command/cadastrar", "POST", "UTF-8");
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
            /** TODO: Modificações a serem feitas:
             *      Esta parte foi feita com base no código do professor.
             *      Para adaptar para nosso trabalho é necessário saber o que a api do João vai nos entregar
             *      A API está com bug, então vai demorar a saber.
             *      quando solbermos devemos validar se está correto.
             *      Se estiver correto devemos guardar tanto o tolking quanto o login e a senha na APP*/
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
  /** MÉTODO LOGIN QUE FAZ A VERIFICAÇÃO NA API E RETORNA SE O USUÁRIO FINALIZOU O LOGIN OU NÃO
    /***/
    public boolean login(String email, String senha ){

        // Resquisição para o servidor

        HttpRequest httpRequest= new HttpRequest(Config.INNATURE_URL+"command/login", "POST", "UTF-8");
        /** Adição as tabelas
         * As informações obtidas serão */
        httpRequest.addParam("email", email);
        httpRequest.addParam("senha", senha);

        String result="";

        try {
            // executa a requisição HTTP. É neste momento que o servidor é consultado
            InputStream is = httpRequest.execute();



            result = Util.inputStream2String(is,"UTF-8");
            Log.i("HTTP RESULTADO DO CADASTRO",result);

            JSONObject jsonObject = new JSONObject(result);

            // Obtem vo valor da chave sucesso para verificar se a ação ocorreu da forma esperada
            int sucesso= jsonObject.getInt("sucesso");

            // ferificação que informa se o usuário fo autenticaddo
            if (sucesso==1){
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
         }

        return false;
    }

    /**
     *Este método será usado para puxar do Banco de Dados uma quantidae determinada, pelo loadSize, de plantas.
     * O método para carregar os comentários será semelhante.
     * Para visualizar as plantas tanto quanto para visualizar comentários não será necessário login.*/


    //TODO: Fazer Função loadPlanta
    public List<Planta> loadPlanta(Integer limit, Integer offSet) {
        //o limit é a quantidade de itens (neste caso plantas) que requisitaremos do banco de dados
        //o offSet sete determina a partir de qual item (neste caso planta) pegaremos a quantidade requisitada "limit"

        /**Não é necessário validação de usuário (login, senha ou tolking) para carregar as plantas*/

        // cria a lista de produtos incicialmente vazia, que será retornada como resultado
        List<Planta> plantaList = new ArrayList<>();

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/plantas", "GET", "UTF-8");
//        httpRequest.addParam("limit", limit.toString());
//        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

//        // Tentativa de Conexão
//        try{
//            //executando a requisição
//            InputStream is = httpRequest.execute();//Este erro é devido a falta do catch
//            //resultado provavelmente será em uma string de formato JSON que preciso perguntar ao João como virá
//            /** TODO: O resulte receberá uma string que virá em formato JSON
//             *      Depois de transformar a string em objeto JSON devemos manipular estes dados de maneira
//             *      que devolvamos uma lista de Objetos contendo as informações pertinentes a dada planta.*/
//            result = Util.inputStream2String(is,"UTF-8");//Este erro é devido a falta do catch
//
//            //fechando conecção com servidor
//            httpRequest.finish();//Este erro é devido a falta do catch
//
//            Log.i("HTTP RESULTADO   DA REQUISIÇÃO",result);
//        }//Este erro é devido a falta do catch

        return null;
    } //este erro é devido a falta de return
    /** Método que cria a requisição httppara obter as informações das plantas
     * LoadPlantaDetail
     */

    Planta loadPlantaDetail(int id){

        // CRIAÇÃO DA REQUISIÇÃO HTTP E ADICIONA OS PARÂMETROS QUE SERÃO ENVIADOS AO BANCO
        HttpRequest httpRequest=new HttpRequest(Config.INNATURE_URL+"command/plantas/id","GET","UTF-8");
        httpRequest.addParam("id".id);

        String result= "";
        try{
            // executa arequisição HTTP.Momento em que o servidor web é conectado

            InputStream is =httpRequest.execute();

            result= Util.inputStream2String(is,"UTF-8");

            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);
            //Veificarseo JSONbject é o mesmo no nosso caso

            JSONObject jsonObject = new JSONObject(result);
            int sucess=  jsonObject.getInt("Sucesso");


            if(sucess==1){
                int id= jsonObject.getInt("id");
                String nome=jsonObject.getString("nome");
                String nomeCientifico= jsonObject.getString("nomeCientifico");
                //Bitmap imagem= jsonObject.
                String informacao= jsonObject.getString("informações");
                ArrayList<Preparo> =jsonObject.getJSONArray("preparo") ;
                ArrayList<Comentario>= jsonObject.getJSONArray("comentarios") ;

                //Cria um objeto Planta eguarda asseguintes iformações

                Planta p = new Planta();
                



            }

        }

    }
}
