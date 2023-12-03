package joao.nicolly.daianny.elisa.model;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlantaViewModel  extends AndroidViewModel {
    public PlantaViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Método que cria e executa as requisições para o banco
     * Solicita o id do produto e retorna um LiveData
     */

    /**Nick precisei colocar return null e comentar algumas partes para testar o código.
     * Também modifiquei as informações recebidas pois no banco de dados as plantas apenas possuem id, nome e imagem.
     * então estou tendo que mudar isso no código inteiro, já mudei na classe planta.*/

    public LiveData<Planta> getPlanta(int  id) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Planta> planta = new MutableLiveData<>();

       // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable(){
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */

            @Override
            public void run(){

                // Criação de uma instância de InNatureRepository
                InNatureRepository inNatureRepository= new InNatureRepository((getApplication()));

                /**Método que loadPlantaDetaisl cotém os dados da planta
                 * retorna um objeto planta
                 */
                Planta p = inNatureRepository.loadPlantaDetail(id);

                 //    Resultado da operação dentro do LiveData
                planta.postValue(p);
            }
        });

        return planta;
    }
}
