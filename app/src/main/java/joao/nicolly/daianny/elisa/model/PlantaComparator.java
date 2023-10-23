package joao.nicolly.daianny.elisa.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PlantaComparator extends DiffUtil.ItemCallback<Planta> {

    @Override
    public boolean areItemsTheSame(@NonNull Planta oldItem, @NonNull Planta newItem) {
        return oldItem.getNome_cientifico().equals((newItem.getNome_cientifico()));
    }

    @Override
    public boolean areContentsTheSame(@NonNull Planta oldItem, @NonNull Planta newItem) {
        return oldItem.getNome_cientifico().equals((newItem.getNome_cientifico()));
    }
}
