package joao.nicolly.daianny.elisa.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.model.Planta;

public class PlantaComparator extends DiffUtil.ItemCallback<Planta> {

    //Aqui checamos o id dos itens recebidos para seber se são identicos

    @Override
    public boolean areItemsTheSame(@NonNull Planta oldItem, @NonNull Planta newItem) {
        String oldItemStr = Integer.toString(oldItem.getId());
        String newItemStr = Integer.toString(newItem.getId());
        return oldItemStr.equals(newItemStr);
    }

    //Aqui comparamos o conteúdo de cada elemento para saber se possuem as mesmas coisas;

    //O único conteúdo que não está sendo comparado no momento é a imagem pois está em bitmap.

    @Override
    public boolean areContentsTheSame(@NonNull Planta oldItem, @NonNull Planta newItem) {
        String oldItemStr = Integer.toString(oldItem.getId());
        String newItemStr = Integer.toString(newItem.getId());
        return oldItemStr.equals(newItemStr) &&
                oldItem.getNome().equals(newItem.getNome()) &&
                oldItem.getNome_cientifico().equals((newItem.getNome_cientifico())) &&
                oldItem.getInformacao().equals(newItem.getInformacao()) ;
    }
}
