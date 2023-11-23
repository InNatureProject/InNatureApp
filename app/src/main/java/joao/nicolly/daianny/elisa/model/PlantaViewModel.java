package joao.nicolly.daianny.elisa.model;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public LiveData<Planta> getPlanta(int id, String nome, String nomeCientifico, Bitmap imagem, String informacao, ArrayList<Preparo> preparos, ArrayList<Comentario> comentarios) {

       // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable(){

            @Override
            public void run(){
                // Criação de uma instância de InNatureRepository
                //classe com os métodos que se comunicamcom o servidor web
                 Planta p = InNatureRepository.LoadPlantaDetaial(id);

                 //    Resultado da operação dentro do LiveData

                plantaDetailLD.postValue(p);


            }

        });

        return plantaDetailLD;
    }
}
