package joao.nicolly.daianny.elisa.model.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import joao.nicolly.daianny.elisa.model.InNatureRepository;
import joao.nicolly.daianny.elisa.model.objetos.ReceitaPreparo;

public class ImageUserViewModel extends AndroidViewModel {
    public ImageUserViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getUrl(){
        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<String> url = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {
            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {
                // Criamos uma instância de InNatureRepository. É dentro dessa classe que estão os
                // métodos que se comunicam com o servidor web.
                InNatureRepository inNatureRepository = new InNatureRepository(getApplication());

                // O método loadImageUser obtem os dados detalhados de um produto junto ao servidor.
                // Ele retorna uma String contendo url, que contém os dados detalhados do produto.
                String u = inNatureRepository.loadImageUser();
                //TODO:,,,,

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                url.postValue(u);
            }
        });
        return url;
    }
}
