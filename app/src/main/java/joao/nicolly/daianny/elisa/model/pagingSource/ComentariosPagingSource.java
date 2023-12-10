package joao.nicolly.daianny.elisa.model.pagingSource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingState;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import joao.nicolly.daianny.elisa.model.InNatureRepository;
import joao.nicolly.daianny.elisa.model.objetos.Comentario;
import joao.nicolly.daianny.elisa.model.objetos.Planta;

public class ComentariosPagingSource extends ListenableFuturePagingSource<Integer, Comentario> {
    InNatureRepository inNatureRepository;
    Integer initialLoadSize = 0;
    int id;

    public ComentariosPagingSource(InNatureRepository inNatureRepository,int id){
        this.inNatureRepository = inNatureRepository;
        this.id = id;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Comentario> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, Comentario>> loadFuture(@NonNull LoadParams<Integer> loadParams) {
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

        ListenableFuture<LoadResult<Integer, Comentario>> lf = service.submit(new Callable<LoadResult<Integer,Comentario>>() {

            /**
             * O método call pega os próximos 'n' itens a serem exibidos no formato de uma lista de plantas
             * este método chama o método loadPlanta de InNatureRepository*/

            @Override
            public LoadResult<Integer, Comentario> call() throws Exception {
                List<Comentario> comentariosList = null;
                //a explicação do que é loadParams.getLoadSize e do que é finalOffSet está em loadPlanta
                comentariosList = inNatureRepository.loadComentarios(loadParams.getLoadSize(),finalOffSet);//TODO
                Integer nextKey = null;

                //Se o tamanho da lista for
                if(comentariosList.size() >= loadParams.getLoadSize()){
                    nextKey = finalNextPageNumber + 1;
                }
                return new LoadResult.Page<Integer,Comentario>(comentariosList, null,nextKey);


            }
        });
        return lf;
    }
}
