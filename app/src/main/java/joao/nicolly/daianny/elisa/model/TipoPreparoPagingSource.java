package joao.nicolly.daianny.elisa.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class TipoPreparoPagingSource extends ListenableFuturePagingSource<Integer,TipoPreparo> {


    //Variáveis
    InNatureRepository inNatureRepository;
    Integer initialLoadSize = 0;
    int id;

    //CONSTRUTOR
    public TipoPreparoPagingSource(InNatureRepository inNatureRepository,int id){
        this.inNatureRepository = inNatureRepository;
        this.id = id;
    }

    //MÉTODOS OBRIGATÓRIOS

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, TipoPreparo> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PagingSource.LoadResult<Integer, TipoPreparo>> loadFuture(@NonNull PagingSource.LoadParams<Integer> loadParams) {
        Integer nextPageNumber = loadParams.getKey();
        if(nextPageNumber == null){
            nextPageNumber = 1;
            initialLoadSize = loadParams.getLoadSize();
        }
        Integer offSet = 0;
        if(nextPageNumber == 2){
            offSet = initialLoadSize;
        }
        else{
            offSet = ((nextPageNumber-1) * loadParams.getLoadSize()) + (initialLoadSize - loadParams.getLoadSize());
        }

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

        Integer finalOffSet = offSet;
        Integer finalNextPageNumber = nextPageNumber;

        ListenableFuture<PagingSource.LoadResult<Integer,TipoPreparo>> lf = service.submit(new Callable<PagingSource.LoadResult<Integer,TipoPreparo>>() {

            /**
             * O método call pega os próximos 'n' itens a serem exibidos no formato de uma lista de TipoPreparos
             * este método chama o método loadTipoPreparos de InNatureRepository*/

            @Override
            public PagingSource.LoadResult<Integer, TipoPreparo> call() throws Exception {
                List<TipoPreparo> tipoPreparoList = null;
                //a explicação do que é loadParams.getLoadSize e do que é finalOffSet está em loadPlanta
                tipoPreparoList = inNatureRepository.loadTipoPreparos(loadParams.getLoadSize(),finalOffSet,id);
                Integer nextKey = null;

                //Se o tamanho da lista for
                if(tipoPreparoList.size() >= loadParams.getLoadSize()){
                    nextKey = finalNextPageNumber + 1;
                }
                return new PagingSource.LoadResult.Page<Integer,TipoPreparo>(tipoPreparoList, null,nextKey);


            }
        });
        return lf;
    }
}
