package joao.nicolly.daianny.elisa.model.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import joao.nicolly.daianny.elisa.model.InNatureRepository;

public class SetImageViewModel extends AndroidViewModel {
    public SetImageViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Boolean> setNewImageUser(String url){
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

                /** O Método setImageUser pega o url da imagem e envia uma requisicão post
                 * retorna um JSONObject contendo um boolean
                 * true caso a alteração tenha ocorrido e false caso não
                 */
                Boolean b = inNatureRepository.setImageUser(url);

                //    Resultado da operação dentro do LiveData
                booleanMutableLiveData.postValue(b);
            }
        });
        return booleanMutableLiveData;
    }
}
