package joao.nicolly.daianny.elisa.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.activity.PreparosActivity;
import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.TipoPreparo;
import kotlinx.coroutines.CoroutineDispatcher;

public class PreparosAdapter extends PagingDataAdapter<TipoPreparo,MyViewHolder> {

    /** Para conseguirmos fazer o intent é necessário que armazenemos o PreparosActivity aqui
     * Também será necessário fazer o método intent no PreparosActivity*/

    PreparosActivity preparosActivity;
    public PreparosAdapter(PreparosActivity preparosActivity, @NonNull DiffUtil.ItemCallback<TipoPreparo> diffCallback) {
        super(diffCallback);
        this.preparosActivity = preparosActivity;
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
