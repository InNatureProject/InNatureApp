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

import joao.nicolly.daianny.elisa.model.InNatureRepository;
import joao.nicolly.daianny.elisa.model.objetos.Planta;
import joao.nicolly.daianny.elisa.model.pagingSource.PlantasFavoritasPagingSource;
import kotlinx.coroutines.CoroutineScope;

public class PlantasFavoritasViewModel extends AndroidViewModel {
    // VARIÁVEIS
    int navigationOpSelected;
    private LiveData<PagingData<Planta>> pageLv;

    //CONSTRUTOR
    public PlantasFavoritasViewModel(@NonNull Application application) {
        super(application);
        //criação e inicialização de variáveis
        InNatureRepository inNatureRepository = new InNatureRepository(application);
        PlantasFavoritasPagingSource plantasFavoritasPadingSource = new PlantasFavoritasPagingSource(inNatureRepository);

        Pager<Integer, Planta> pager = new Pager(new PagingConfig(20), () -> plantasFavoritasPadingSource);
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
