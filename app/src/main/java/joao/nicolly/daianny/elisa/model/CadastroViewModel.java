package joao.nicolly.daianny.elisa.model;

import android.app.Application;
import  androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastroViewModel extends AndroidViewModel{

    //Construtor
    public CadastroViewModel(@NonNull Application application){ super(application);}


    public LiveData<Boolean> cadastrar(String nome, String email, String senha) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        //Nova linha de execução
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {
                // TODO checar InNatureRepository porque ainda faltam alguns ajustes
                InNatureRepository inNatureRepository = new InNatureRepository(getApplication());

                boolean b = inNatureRepository.cadastrar(nome, email, senha);

                result.postValue(b);


            }
        });
        return result;
    }
}
