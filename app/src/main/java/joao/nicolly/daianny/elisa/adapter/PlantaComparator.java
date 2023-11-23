package joao.nicolly.daianny.elisa.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.model.Planta;

public class PlantaComparator extends DiffUtil.ItemCallback<Planta> {

    @Override
    public boolean areItemsTheSame(@NonNull Planta oldItem, @NonNull Planta newItem) {
        String oldItemIdStr = Integer.toString(oldItem.getId());
        String newItemIdStr = Integer.toString(newItem.getId());
        return oldItemIdStr.equals(newItemIdStr);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Planta oldItem, @NonNull Planta newItem) {
        String oldItemIdStr = Integer.toString(oldItem.getId());
        String newItemIdStr = Integer.toString(newItem.getId());
        return oldItemIdStr.equals(newItemIdStr) &&
                oldItem.getNome().equals(newItem.getNome());
    }
}
