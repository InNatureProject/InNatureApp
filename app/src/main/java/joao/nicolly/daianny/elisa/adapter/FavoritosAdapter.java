package joao.nicolly.daianny.elisa.adapter;

import android.view.ViewGroup;

import joao.nicolly.daianny.elisa.model.Planta;
import kotlinx.coroutines.CoroutineDispatcher;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class FavoritosAdapter extends PagingDataAdapter<Planta,MyViewHolder>{
    public FavoritosAdapter(@NonNull DiffUtil.ItemCallback<Planta> diffCallback, @NonNull CoroutineDispatcher mainDispatcher) {
        super(diffCallback, mainDispatcher);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }
}
