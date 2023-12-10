package joao.nicolly.daianny.elisa.model.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import joao.nicolly.daianny.elisa.model.InNatureRepository;

public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //Método que cria e executa uma reuisição para o servidor

    public LiveData<Boolean> login(String email, String senha ){
        MutableLiveData<Boolean> result= new MutableLiveData<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                InNatureRepository inNatureRepository= new  InNatureRepository(getApplication());
                boolean b = inNatureRepository.login(email,senha);

                result.postValue(b);
            }
        });
        return result;

    }
}
