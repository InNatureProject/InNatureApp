package joao.nicolly.daianny.elisa.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import joao.nicolly.daianny.elisa.model.objetos.Comentario;
import joao.nicolly.daianny.elisa.model.objetos.Planta;
import joao.nicolly.daianny.elisa.model.objetos.ReceitaPreparo;
import joao.nicolly.daianny.elisa.model.objetos.TipoPreparo;
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

            /**Resulte virá com uma string contendo um JSON
             * result = "{"Token":"Token gerado pelo servidor que devemos armadenar","result":true}"*/
            JSONObject jsonObject = new JSONObject(result);


            Boolean resultado = jsonObject.getBoolean("result");

            if(resultado){
                /**Aqui pegamos o token recebido e o armazenamos*/
                String tolken = jsonObject.getString("Token");
                Config.setTolken(context,tolken);
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


        HttpRequest httpRequest= new HttpRequest(Config.INNATURE_URL+"command/logar", "POST", "UTF-8");
        /** Adição as tabelas
         * As informações obtidas serão */
        httpRequest.addParam("email", email);
        httpRequest.addParam("senha", senha);
        Boolean resultado=false;

        String result="";

        try {
            // executa a requisição HTTP. É neste momento que o servidor é consultado
            InputStream is = httpRequest.execute();

            result = Util.inputStream2String(is,"UTF-8");


            httpRequest.finish();
            Log.i("HTTP RESULTADO DO LOGIN",result);

            JSONObject jsonObject = new JSONObject(result);


            resultado = jsonObject.getBoolean("result");


            if(resultado){
                JSONObject jsonInformacoes = jsonObject.getJSONObject("data");
                String tolken = jsonInformacoes.getString("Token");
                String nome = jsonInformacoes.getString("Nome");
                String imagem= jsonInformacoes.getString("Imagem");

                Config.setTolken(context,tolken);
                Config.setImagem(context,imagem);
                Config.setName(context,nome);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
         }

        return resultado;
    }

    /**
    *Este método será usado para puxar do Banco de Dados uma quantidae determinada, pelo loadSize, de plantas.
    * O método para carregar os comentários será semelhante.
    * Para visualizar as plantas tanto quanto para visualizar comentários não será necessário login.*/

    public List<Comentario> loadComentarios(int id, Integer limit, Integer offSet){
        //o limit é a quantidade de itens que requisitaremos do banco de dados
        //o offSet sete determina a partir de qual item pegaremos a quantidade requisitada "limit"

        /**Não é necessário validação de usuário (login, senha ou token) para carregar as plantas*/

        List<Comentario> comentariosList = new ArrayList<>();
        String sid = Integer.toString(id);

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/getComentarios/" + sid, "GET", "UTF-8");
        /**Como não estamos fazendo paginação no banco de dados não enviamos o limit nem o offSet*/
        //        httpRequest.addParam("limit", limit.toString());
        //        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            // que devolvamos uma lista de Objetos contendo as informações pertinentes a cada planta.

            result = Util.inputStream2String(is,"UTF-8");

            // result conterá a informação de todas as plantas
            // result = [{"cod_plt":1,"nome":"Capim Limão","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/capim-limao.jpg","descricao":null},{"cod_plt":2,"nome":"Camomila","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/camomila.jpg","descricao":null},{"cod_plt":3,"nome":"Hortelã","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/hortela.jpg","descricao":null},{"cod_plt":4,"nome":"Erva-Cidreira","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/erva-cidreira.jpg","descricao":null},{"cod_plt":5,"nome":"Chá Verde","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/cha-verde.jpg","descricao":null}]
            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP RESULTADO   DA REQUISIÇÃO",result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONArray jsonArray = new JSONArray(result);

            // os produtos são obtidos da String JSON e adicionados à lista de
            // plantas a ser retornada como resultado.


            // Cada elemento do JSONArray é um JSONObject que guarda os dados de um comentario
            for(int i = 0; i < jsonArray.length(); i++) {

                // Obtemos o JSONObject referente a um produto
                JSONObject JComentario = jsonArray.getJSONObject(i);

                // Obtemos os dados de um produtos via JSONObject
                int comentId = JComentario.getInt("cod_cmt");
                String nomeAutor = JComentario.getString("nome");
                String comentarioContent = JComentario.getString("descricao");
                String fotoAutor = JComentario.getString("imagem");

                // Criamo um objeto do tipo Product para guardar esses dados
                Comentario comentario = new Comentario(comentId,nomeAutor,fotoAutor,comentarioContent);

                // Adicionamos o objeto product na lista de produtos
                comentariosList.add(comentario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return comentariosList;

    }
    public List<Planta> loadPlantaBuscada( String busque, Integer limit, Integer offSet){
        busque = "ca";
        //o limit é a quantidade de itens (neste caso plantas) que requisitaremos do banco de dados
        //o offSet sete determina a partir de qual item (neste caso planta) pegaremos a quantidade requisitada "limit"

        /**Não é necessário validação de usuário (login, senha ou token) para carregar as plantas*/


        // cria a lista de plantas incicialmente vazia, que será retornada como resultado
        List<Planta> plantaList = new ArrayList<>();

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/searchPlanta/" + busque, "GET", "UTF-8");
        /**Como não estamos fazendo paginação no banco de dados não enviamos o limit nem o offSet*/
        //        httpRequest.addParam("limit", limit.toString());
        //        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            // que devolvamos uma lista de Objetos contendo as informações pertinentes a cada planta.

            result = Util.inputStream2String(is,"UTF-8");

            // result conterá a informação de todas as plantas
            // result = [{"cod_plt":1,"nome":"Capim Limão","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/capim-limao.jpg","descricao":null},{"cod_plt":2,"nome":"Camomila","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/camomila.jpg","descricao":null},{"cod_plt":3,"nome":"Hortelã","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/hortela.jpg","descricao":null},{"cod_plt":4,"nome":"Erva-Cidreira","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/erva-cidreira.jpg","descricao":null},{"cod_plt":5,"nome":"Chá Verde","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/cha-verde.jpg","descricao":null}]
            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP RESULTADO   DA REQUISIÇÃO",result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // os produtos são obtidos da String JSON e adicionados à lista de
            // plantas a ser retornada como resultado.

            if(jsonObject.getBoolean("result")){
                JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("data"));
                // Cada elemento do JSONArray é um JSONObject que guarda os dados de um produto
                for(int i = 0; i < jsonArray.length(); i++) {

                    // Obtemos o JSONObject referente a um produto
                    JSONObject jPlanta = jsonArray.getJSONObject(i);

                    // Obtemos os dados de um produtos via JSONObject
                    int id = jPlanta.getInt("cod_plt");
                    String name = jPlanta.getString("nome");
                    String img = jPlanta.getString("imagem");
                    String desc = jPlanta.getString("descricao");

                    // Criamo um objeto do tipo Product para guardar esses dados
                    Planta planta = new Planta(id,name, img, desc);

                    // Adicionamos o objeto product na lista de produtos
                    plantaList.add(planta);
            }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return plantaList;
    }


    public List<Planta> loadPlanta(Integer limit, Integer offSet) {
        //o limit é a quantidade de itens (neste caso plantas) que requisitaremos do banco de dados
        //o offSet sete determina a partir de qual item (neste caso planta) pegaremos a quantidade requisitada "limit"

        /**Não é necessário validação de usuário (login, senha ou token) para carregar as plantas*/

        // cria a lista de plantas incicialmente vazia, que será retornada como resultado
        List<Planta> plantaList = new ArrayList<>();

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/plantas", "GET", "UTF-8");
        /**Como não estamos fazendo paginação no banco de dados não enviamos o limit nem o offSet*/
        //        httpRequest.addParam("limit", limit.toString());
        //        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            // que devolvamos uma lista de Objetos contendo as informações pertinentes a cada planta.

            result = Util.inputStream2String(is,"UTF-8");

            // result conterá a informação de todas as plantas
            // result = [{"cod_plt":1,"nome":"Capim Limão","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/capim-limao.jpg","descricao":null},{"cod_plt":2,"nome":"Camomila","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/camomila.jpg","descricao":null},{"cod_plt":3,"nome":"Hortelã","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/hortela.jpg","descricao":null},{"cod_plt":4,"nome":"Erva-Cidreira","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/erva-cidreira.jpg","descricao":null},{"cod_plt":5,"nome":"Chá Verde","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/cha-verde.jpg","descricao":null}]
            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP RESULTADO   DA REQUISIÇÃO",result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONArray jsonArray = new JSONArray(result);

            // os produtos são obtidos da String JSON e adicionados à lista de
            // plantas a ser retornada como resultado.


            // Cada elemento do JSONArray é um JSONObject que guarda os dados de um produto
            for(int i = 0; i < jsonArray.length(); i++) {

                // Obtemos o JSONObject referente a um produto
                JSONObject jPlanta = jsonArray.getJSONObject(i);

                // Obtemos os dados de um produtos via JSONObject
                int id = jPlanta.getInt("cod_plt");
                String name = jPlanta.getString("nome");
                String img = jPlanta.getString("imagem");
                String desc = jPlanta.getString("descricao");

                // Criamo um objeto do tipo Product para guardar esses dados
                Planta planta = new Planta(id,name, img, desc);

                // Adicionamos o objeto product na lista de produtos
                plantaList.add(planta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return plantaList;
    }

    public List<Planta> loadPlantasFavoritas(Integer limit, Integer offSet){
        //o limit é a quantidade de itens (neste caso plantas) que requisitaremos do banco de dados
        //o offSet sete determina a partir de qual item (neste caso planta) pegaremos a quantidade requisitada "limit"

        /**Neste caso é necessária a validação do usuário através de Token para carregar as plantas*/

        // cria a lista de plantas incicialmente vazia, que será retornada como resultado
        List<Planta> plantaList = new ArrayList<>();

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/favoritos", "POST", "UTF-8");
        httpRequest.addParam("Token",Config.getTolken(context));
        /**Como não estamos fazendo paginação no banco de dados não enviamos o limit nem o offSet*/
        //        httpRequest.addParam("limit", limit.toString());
        //        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            // que devolvamos uma lista de Objetos contendo as informações pertinentes a cada planta.

            result = Util.inputStream2String(is,"UTF-8");

            // result conterá a informação de todas as plantas
            // result = [{"cod_plt":1,"nome":"Capim Limão","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/capim-limao.jpg","descricao":null},{"cod_plt":2,"nome":"Camomila","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/camomila.jpg","descricao":null},{"cod_plt":3,"nome":"Hortelã","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/hortela.jpg","descricao":null},{"cod_plt":4,"nome":"Erva-Cidreira","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/erva-cidreira.jpg","descricao":null},{"cod_plt":5,"nome":"Chá Verde","imagem":"https://fenixculatra.github.io/PlantasMedicinais/imagens/cha-verde.jpg","descricao":null}]
            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP RESULTADO   DA REQUISIÇÃO",result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);
            //o jsonObject ficará como {"result":true,"data":[]}
            if(jsonObject.getBoolean("result")){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                // os produtos são obtidos da String JSON e adicionados à lista de
                // plantas a ser retornada como resultado.

                // Cada elemento do JSONArray é um JSONObject que guarda os dados de um produto
                for(int i = 0; i < jsonArray.length(); i++) {

                    // Obtemos o JSONObject referente a um produto
                    JSONObject jPlanta = jsonArray.getJSONObject(i);

                    // Obtemos os dados de um produtos via JSONObject
                    int id = jPlanta.getInt("cod_plt");
                    String name = jPlanta.getString("nome");
                    String img = jPlanta.getString("imagem");
                    String desc = jPlanta.getString("descricao");

                    // Criamo um objeto do tipo Product para guardar esses dados
                    Planta planta = new Planta(id,name, img, desc);

                    // Adicionamos o objeto product na lista de produtos
                    plantaList.add(planta);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return plantaList;
    }
    /**Metodo que pega os tipos de preparo de uma planta*/

    public List<TipoPreparo> loadTipoPreparos(Integer limit, Integer offSet, int id){
        //o limit é a quantidade de itens (neste caso TipoPreparo) que requisitaremos do banco de dados
        //o offSet sete determina a partir de qual item pegaremos a quantidade requisitada "limit"

        //Precisamos passar o id como string então primeiro convertemos o id para string, já que é um int
        String sid = Integer.toString(id);

        /**Não é necessário validação de usuário (login, senha ou token) para carregar as plantas*/

        // cria a lista de plantas incicialmente vazia, que será retornada como resultado
        List<TipoPreparo> tipoPreparoList = new ArrayList<>();

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/plantapreparo/"+ sid, "GET", "UTF-8");
        httpRequest.addParam("id", sid);

        /**Como não estamos fazendo paginação no banco de dados não enviamos o limit nem o offSet*/
        //        httpRequest.addParam("limit", limit.toString());
        //        httpRequest.addParam("offset",offSet.toString());

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";
        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos uma lista de Objetos contendo as informações pertinentes a cada tipoPreparo

            result = Util.inputStream2String(is,"UTF-8");

            // result conterá a informação de todos os tipoPreparo
            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP RESULTADO  DA REQUISIÇÃO",result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONArray jsonArray = new JSONArray(result);

            // Cada elemento do JSONArray é um JSONObject que guarda os dados de um tipoPreparo
            for(int i = 0; i < jsonArray.length(); i++) {

                // Obtemos o JSONObject referente a um TipoPreparo
                JSONObject jTipoPreparo = jsonArray.getJSONObject(i);

                // Obtemos os dados de um produtos via JSONObject

                int idTipoPreparo = i;
                String tipoP = jTipoPreparo.getString("tipo_preparo");
                String name = jTipoPreparo.getString("titulo");

                // Criamo um objeto do TipoPreparo para guardar esses dados
                TipoPreparo tipoPreparo = new TipoPreparo(id,tipoP,name,idTipoPreparo);


                // Adicionamos o objeto product na lista de produtos
                tipoPreparoList.add(tipoPreparo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return tipoPreparoList;
    }
    public ReceitaPreparo loadReceita(int idPlanta, int idTipoPreparo){

        //Precisamos passar o id como string então primeiro convertemos o id para string, já que é um int
        String sid = Integer.toString(idPlanta);
        ReceitaPreparo receitaPreparo;

        //Criando as variaveis dos elementos de ReceitaPreparo
        String titulo = "";
        String receita = "";
        JSONArray Jindicacoes = new JSONArray();
        JSONArray Jcontraindicacoes = new JSONArray();
        JSONArray Jefeitoscolaterais = new JSONArray();
        ArrayList<String> indicacoes = new ArrayList<>();
        ArrayList<String> contraindicacoes = new ArrayList<>();
        ArrayList<String> efeitoscolaterais = new ArrayList<>();


        /**Não é necessário validação de usuário (login, senha ou token) para carregar a receita*/

        // Cria uma requisição HTTP a adiciona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL +"command/plantapreparo/"+ sid, "GET", "UTF-8");
        httpRequest.addParam("id", sid);

        //String onde será guardado o resultado retornado pelo servidor
        String result = "";

        // Tentativa de Conexão
        try{
            //executando a requisição
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos uma lista de Objetos contendo as informações pertinentes a cada tipoPreparo

            result = Util.inputStream2String(is,"UTF-8");

            // result conterá a informação de todos os tipoPreparo
            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP RESULTADO  DA REQUISIÇÃO",result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONArray jsonArray = new JSONArray(result);

            // Obtemos o JSONObject referente a uma receita
            JSONObject JReceita = jsonArray.getJSONObject(idTipoPreparo);

            //Aqui pegamos os elementos de dentro do objeto JSON e armazenamos nas variáveis
            titulo = JReceita.getString("titulo");
            receita = JReceita.getString("receita");
            Jindicacoes = JReceita.getJSONArray("indicacao");
            Jcontraindicacoes = JReceita.getJSONArray("contraindicacao");
            Jefeitoscolaterais = JReceita.getJSONArray("efeito colateral");
            for(int i = 0; i < Jindicacoes.length() ;i++){
                indicacoes.add(Jindicacoes.getString(i));
            }
            for(int i = 0;i < Jcontraindicacoes.length();i++){
                contraindicacoes.add(Jcontraindicacoes.getString(i));
            }
            for(int i = 0;i < Jefeitoscolaterais.length();i++){
                efeitoscolaterais.add(Jefeitoscolaterais.getString(i));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        receitaPreparo = new ReceitaPreparo(titulo,receita,indicacoes,contraindicacoes,efeitoscolaterais);
        return receitaPreparo;
    }

    /** Método que cria a requisição htt para obter as informações das plantas
    * LoadPlantaDetail
    */

    public Planta loadPlantaDetail(int id){

        String sid = Integer.toString(id);

        Planta p;
        int codPlanta = 0;
        String nome = "";
        String imagem = "";
        String descricao = "";

        // CRIAÇÃO DA REQUISIÇÃO HTTP E ADICIONA OS PARÂMETROS QUE SERÃO ENVIADOS AO BANCO
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL+"command/planta/" + sid,"GET","UTF-8");
        httpRequest.addParam("id", sid);

        String result= "";
        try{
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos um Objeto contendo informações da planta

            result = Util.inputStream2String(is,"UTF-8");

            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);
            //Veificarseo JSONbject é o mesmo no nosso caso

            // A classe JSONArray recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONArray jsonArray = new JSONArray(result);

            // Obtemos o JSONObject referente a uma planta
            JSONObject JPlanta = jsonArray.getJSONObject(0);

            codPlanta = JPlanta.getInt("cod_plt");
            nome = JPlanta.getString("nome");
            imagem = JPlanta.getString("imagem");
            descricao = JPlanta.getString("descricao");


        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        p = new Planta(codPlanta, nome, imagem, descricao);
        return p;
    }

    public Boolean setImageUser(String url){


        Boolean b = false;
        // CRIAÇÃO DA REQUISIÇÃO HTTP E ADICIONA OS PARÂMETROS QUE SERÃO ENVIADOS AO BANCO
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL+"command/setImagem","POST","UTF-8");
        httpRequest.addParam("Token",Config.getTolken(context));
        httpRequest.addParam("url", url);

        String result= "";
        try{
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos um Objeto contendo informações da planta

            result = Util.inputStream2String(is,"UTF-8");

            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);
            //Veificarseo JSONbject é o mesmo no nosso caso

            // A classe JSONArray recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.

            //receberemos json no formato:{result:true,info:"string"}
            JSONObject jsonObject = new JSONObject(result);

            // Obtemos o JSONObject referente a uma planta
            b = jsonObject.getBoolean("result");

        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public Boolean ehFavorito(int id){
        String sid = Integer.toString(id);
        Boolean b = false;

        // CRIAÇÃO DA REQUISIÇÃO HTTP E ADICIONA OS PARÂMETROS QUE SERÃO ENVIADOS AO BANCO
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL+"command/ehFavorito","POST","UTF-8");
        httpRequest.addParam("Token",Config.getTolken(context));
        httpRequest.addParam("Planta", sid);

        String result= "";
        try{
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos um Objeto contendo informações da planta

            result = Util.inputStream2String(is,"UTF-8");

            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);
            //Veificarseo JSONbject é o mesmo no nosso caso

            // A classe JSONArray recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.

            //receberemos json no formato:{result:true,info:"string"}
            JSONObject jsonObject = new JSONObject(result);

            // Obtemos o JSONObject referente a uma planta
            b = jsonObject.getBoolean("result");

        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
    public  Boolean addComent(int id, String contentComent){
        String sid = Integer.toString(id);
        Boolean b = false;

        // CRIAÇÃO DA REQUISIÇÃO HTTP E ADICIONA OS PARÂMETROS QUE SERÃO ENVIADOS AO BANCO
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL+"command/comentar","POST","UTF-8");
        httpRequest.addParam("Token",Config.getTolken(context));
        httpRequest.addParam("Planta", sid);
        httpRequest.addParam("Descricao",contentComent);

        String result= "";
        try{
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos um Objeto contendo informações da planta

            result = Util.inputStream2String(is,"UTF-8");

            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);
            //Veificarseo JSONbject é o mesmo no nosso caso

            // A classe JSONArray recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.

            //receberemos json no formato:{result:true,info:"string"}
            JSONObject jsonObject = new JSONObject(result);

            // Obtemos o JSONObject referente a uma planta
            b = jsonObject.getBoolean("result");

        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**Método responsável pela função de favoritar*/

    public Boolean favoritarPlanta(int id){

        /**Sempre que o usuário clicar no f-botão favoritar ele fovoritará ou desfavoritará a planta no banco de dados*/

        String sid = Integer.toString(id);
        Boolean b = false;

        // CRIAÇÃO DA REQUISIÇÃO HTTP E ADICIONA OS PARÂMETROS QUE SERÃO ENVIADOS AO BANCO
        HttpRequest httpRequest = new HttpRequest(Config.INNATURE_URL+"command/favoritar","POST","UTF-8");
        httpRequest.addParam("Token",Config.getTolken(context));
        httpRequest.addParam("Planta", sid);

        String result= "";
        try{
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            //resultado provavelmente será em uma string de formato JSON que devemos manipular de maneira
            //que devolvamos um Objeto contendo informações da planta

            result = Util.inputStream2String(is,"UTF-8");

            //fechando conecção com servidor
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);
            //Veificarseo JSONbject é o mesmo no nosso caso

            // A classe JSONArray recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.

            //receberemos json no formato:{result:true,info:"string"}
            JSONObject jsonObject = new JSONObject(result);

            // Obtemos o JSONObject referente a uma planta
            b = jsonObject.getBoolean("result");

        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

}
