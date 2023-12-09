package joao.nicolly.daianny.elisa.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.model.objetos.TipoPreparo;

public class ComentarioComparator extends DiffUtil.ItemCallback<TipoPreparo> {
    @Override
    public boolean areItemsTheSame(@NonNull TipoPreparo oldItem, @NonNull TipoPreparo newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull TipoPreparo oldItem, @NonNull TipoPreparo newItem) {
        return false;
    }
}
