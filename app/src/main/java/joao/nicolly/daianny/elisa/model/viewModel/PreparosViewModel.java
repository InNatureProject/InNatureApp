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
import joao.nicolly.daianny.elisa.model.pagingSource.TipoPreparoPagingSource;
import joao.nicolly.daianny.elisa.model.objetos.TipoPreparo;
import kotlinx.coroutines.CoroutineScope;

public class PreparosViewModel extends AndroidViewModel {

    private LiveData<PagingData<TipoPreparo>> tipoPreparos;
    private int id;
    public PreparosViewModel(@NonNull Application application) {
        super(application);

        // Abaixo configuramos o uso da biblioteca de paginação Paging 3, assim como foi feito na
        // atividade Galeria Pública
        InNatureRepository inNatureRepository = new InNatureRepository(getApplication());
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, TipoPreparo> pager = new Pager(new PagingConfig(10), () -> new TipoPreparoPagingSource(inNatureRepository, id));
        tipoPreparos = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<TipoPreparo>> getTipoPreparos() {return tipoPreparos;}

    public void putId(int id) {
        this.id = id;
    }
}
