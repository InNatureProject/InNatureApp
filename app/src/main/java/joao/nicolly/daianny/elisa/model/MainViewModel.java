package joao.nicolly.daianny.elisa.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import joao.nicolly.daianny.elisa.R;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends AndroidViewModel {

    // VARIÁVEIS
    int navigationOpSelected = R.id.homeViewOp;
    LiveData<PagingData<Planta>> pageLv;

    //CONSTRUTOR
    public MainViewModel(@NonNull Application application) {

        super(application);
        InNatureRepository inNatureRepository = new InNatureRepository(application);
        InNaturePagingSource inNaturePagingSource = new InNaturePagingSource(inNatureRepository);

        Pager<Integer, Planta> pager = new Pager(new PagingConfig(20), () -> inNaturePagingSource);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);

        pageLv = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    //METODOS
    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }

    public LiveData<PagingData<Planta>> getPageLv() {
        return this.pageLv;
    }

}