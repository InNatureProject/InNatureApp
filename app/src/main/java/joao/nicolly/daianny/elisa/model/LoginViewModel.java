package joao.nicolly.daianny.elisa.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutorService;

public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //Método que cria e executa uma reuisição para o servidor

    public LiveData<Boolean> login(String email, String senha ){
        MutableLiveData<Boolean> result= new MultableLiveData<>();
        ExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                ProductsRepository productsRepository= new ProductsRepository(getApplication());
                boolean b= productsRepository.login(email,senha);
                result.postValue(b);
            }
        });
        return result;

    }
}
