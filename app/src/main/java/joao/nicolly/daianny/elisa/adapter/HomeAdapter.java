package joao.nicolly.daianny.elisa.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.model.Planta;
import kotlinx.coroutines.CoroutineDispatcher;

public class HomeAdapter extends PagingDataAdapter<Planta,MyViewHolder> {


    public HomeAdapter(@NonNull DiffUtil.ItemCallback<Planta> diffCallback) {
        super(diffCallback);
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
