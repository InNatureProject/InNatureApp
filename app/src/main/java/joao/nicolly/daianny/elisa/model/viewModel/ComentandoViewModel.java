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
import joao.nicolly.daianny.elisa.model.objetos.Comentario;
import kotlinx.coroutines.CoroutineScope;

public class ComentandoViewModel extends AndroidViewModel {

    private LiveData<PagingData<Comentario>> comentarios;
    private int id;
    public ComentandoViewModel(@NonNull Application application) {
        super(application);
        // Abaixo configuramos o uso da biblioteca de paginação Paging 3, assim como foi feito na
        // atividade Galeria Pública
        InNatureRepository inNatureRepository = new InNatureRepository(getApplication());
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Comentario> pager = new Pager(new PagingConfig(20), () -> new ComentariosPagingSource(inNatureRepository, id));
        comentarios = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        //TODO: Fazer ComentariosPagingSource
    }
}
