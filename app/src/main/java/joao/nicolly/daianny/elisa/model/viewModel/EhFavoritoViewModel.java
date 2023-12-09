package joao.nicolly.daianny.elisa.model.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import joao.nicolly.daianny.elisa.model.InNatureRepository;

public class EhFavoritoViewModel extends AndroidViewModel {
    public EhFavoritoViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Método que cria e executa as requisições para o banco
     * Solicita o id do planta e retorna um LiveData
     */
    public LiveData<Boolean> ehPlantaFavorita(int id){
        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // Criação de uma instância de InNatureRepository
                InNatureRepository inNatureRepository = new InNatureRepository(getApplication());

                /**Método avoritarPlanta pega o id da planta e envia uma requisicão post
                 * retorna um JSONObject contendo um boolean
                 * true caso a alteração tenha ocorrido e false caso não
                 */
                Boolean b = inNatureRepository.ehFavorito(id);

                //    Resultado da operação dentro do LiveData
                booleanMutableLiveData.postValue(b);
            }
        });
        return booleanMutableLiveData;
    }
}
