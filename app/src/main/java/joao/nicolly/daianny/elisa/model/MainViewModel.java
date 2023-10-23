package joao.nicolly.daianny.elisa.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagingData;

import joao.nicolly.daianny.elisa.R;

public class MainViewModel extends AndroidViewModel {//atualizando
    int navigationOpSelected = R.id.homeViewOp;
    LiveData<PagingData<Planta>> pageLv;


    public MainViewModel(@NonNull Application application){

        super(application);

        InNatureRepository inNatureRepository = new InNatureRepository(application);
        InNaturePagingDource
    }
    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }
}
