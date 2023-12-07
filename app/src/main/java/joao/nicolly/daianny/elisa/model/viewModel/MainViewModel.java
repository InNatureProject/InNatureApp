package joao.nicolly.daianny.elisa.model.viewModel;

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
import joao.nicolly.daianny.elisa.model.InNatureRepository;
import joao.nicolly.daianny.elisa.model.pagingSource.PlantaPagingSource;
import joao.nicolly.daianny.elisa.model.objetos.Planta;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends AndroidViewModel {

    // VARIÁVEIS
    int navigationOpSelected = R.id.homeViewOp;
    private LiveData<PagingData<Planta>> pageLv;

    //CONSTRUTOR
    public MainViewModel(@NonNull Application application) {

        super(application);
        //criação e inicialização de variáveis
        InNatureRepository inNatureRepository = new InNatureRepository(application);
        PlantaPagingSource plantaPagingSource = new PlantaPagingSource(inNatureRepository);

        Pager<Integer, Planta> pager = new Pager(new PagingConfig(20), () -> plantaPagingSource);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);

        pageLv = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    //METODOS

    /** Seta a opção de seleção*/
    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }

    /**retora um */
    public LiveData<PagingData<Planta>> getPageLv() {
        return this.pageLv;
    }

}