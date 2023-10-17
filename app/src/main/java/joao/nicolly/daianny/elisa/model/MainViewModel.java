package joao.nicolly.daianny.elisa.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import joao.nicolly.daianny.elisa.R;

public class MainViewModel extends AndroidViewModel {//atualizando
    int navigationOpSelected = R.id.homeViewOp;


    public MainViewModel(@NonNull Application application){
        super(application);
    }
    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }
}
