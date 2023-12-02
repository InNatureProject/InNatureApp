package joao.nicolly.daianny.elisa.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.TipoPreparo;

public class TipoPreparosComparator extends DiffUtil.ItemCallback<TipoPreparo> {
    @Override
    public boolean areItemsTheSame(@NonNull TipoPreparo oldItem, @NonNull TipoPreparo newItem) {
        String oldItemIdStr = Integer.toString(oldItem.getId());
        String newItemIdStr = Integer.toString(newItem.getId());
        return oldItemIdStr.equals(newItemIdStr);
    }

    @Override
    public boolean areContentsTheSame(@NonNull TipoPreparo oldItem, @NonNull TipoPreparo newItem) {
        String oldItemIdStr = Integer.toString(oldItem.getId());
        String newItemIdStr = Integer.toString(newItem.getId());
        return oldItemIdStr.equals(newItemIdStr) &&
                oldItem.getCod_preparo().equals(newItem.getCod_preparo()) &&
                oldItem.getNome().equals(newItem.getNome());
    }
}
