package joao.nicolly.daianny.elisa.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReceitaPreparoViewModel extends AndroidViewModel {
    public ReceitaPreparoViewModel(@NonNull Application application) {
        super(application);
    }
    /**
     * Método que cria e executa uma requisição ao servidor web para obter os detalhes de uma receita
     * na base de dados do servidor
     * @param idPlanta id da planta que se quer obter o preparo
     * @param idTipoPreparo id do preparo contendo a receita
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */

    public LiveData<ReceitaPreparo> getReceita(int idPlanta,int idTipoPreparo){
        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<ReceitaPreparo> receita = new MutableLiveData<>();

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
                // Criamos uma instância de ProductsRepository. É dentro dessa classe que estão os
                // métodos que se comunicam com o servidor web.
                InNatureRepository inNatureRepository = new InNatureRepository(getApplication());

                // O método loadProductDetail obtem os dados detalhados de um produto junto ao servidor.
                // Ele retorna um objeto do tipo Product, que contém os dados detalhados do produto.
                ReceitaPreparo r = inNatureRepository.loadReceita(idPlanta, idTipoPreparo);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                receita.postValue(r);
            }
        });
        return  receita;
    }
}
