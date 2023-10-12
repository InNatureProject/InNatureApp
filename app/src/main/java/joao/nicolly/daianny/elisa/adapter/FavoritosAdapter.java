package joao.nicolly.daianny.elisa.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import joao.nicolly.daianny.elisa.R;
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_planta,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // Como se fosse para cada planta
        Planta planta = getItem(position);

        //Aqui pegaremos a informação necessárias da planta e a dentro dos devidos elementos do item_planta;

        TextView tvNome = holder.itemView.findViewById(R.id.tvNome);
        tvNome.setText(planta.getNome());

        TextView tvNomeCientifico = holder.itemView.findViewById(R.id.tvNoomeCientifico);
        tvNomeCientifico.setText(planta.getNome_cientifico());

        TextView tvDescricaoPlanta = holder.itemView.findViewById(R.id.tvDescricaoPlanta);
        tvDescricaoPlanta.setText(planta.getInformacao());

        ImageView imgvImagemPlanta = holder.itemView.findViewById(R.id.imgvImagemPlanta);
        imgvImagemPlanta.setImageBitmap(planta.getImagem());


    }
}
