package joao.nicolly.daianny.elisa.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import joao.nicolly.daianny.elisa.model.objetos.Comentario;


public class ComentarioComparator extends DiffUtil.ItemCallback<Comentario> {
    @Override
    public boolean areItemsTheSame(@NonNull Comentario oldItem, @NonNull Comentario newItem) {
        String strOldItem = Integer.toString(oldItem.getId());
        String strNewItem = Integer.toString(newItem.getId());
        return strOldItem.equals(strNewItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Comentario oldItem, @NonNull Comentario newItem) {
        String strOldItem = Integer.toString(oldItem.getId());
        String strNewItem = Integer.toString(newItem.getId());
        return strOldItem.equals(strNewItem) &&
                oldItem.getFotoAutor().equals(newItem.getAutor()) &&
                oldItem.getAutor().equals(newItem.getAutor()) &&
                oldItem.getComentario().equals(newItem.getComentario());
    }
}
