package joao.nicolly.daianny.elisa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.fragment.FavoritosFragment;
import joao.nicolly.daianny.elisa.model.Planta;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class FavoritosAdapter extends PagingDataAdapter<Planta,MyViewHolder>{

    FavoritosFragment favoritosFragment;


    //CONSTRUTOR

    public FavoritosAdapter(DiffUtil.ItemCallback<Planta> diffCallback, FavoritosFragment favoritosFragment) {
        super(diffCallback);
        this.favoritosFragment = favoritosFragment;
    }

    //MÉTODOS

    //TODO: Verificar se métodos estão funcionais e onde estão sendo chamados
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

        //colocando a imagem
        ImageView imgvImagemPlanta = holder.itemView.findViewById(R.id.imgvImagemPlanta);
        imgvImagemPlanta.setImageBitmap(planta.getImagem());

        //colocando o nome
        TextView tvNome = holder.itemView.findViewById(R.id.tvNome);
        tvNome.setText(planta.getNome());

        //colocando o nome cientifico
        TextView tvNomeCientifico = holder.itemView.findViewById(R.id.tvNoomeCientifico);
        tvNomeCientifico.setText(planta.getNome_cientifico());

        //colocando a descrição
        TextView tvDescricaoPlanta = holder.itemView.findViewById(R.id.tvDescricaoPlanta);
        tvDescricaoPlanta.setText(planta.getInformacao());


        /**quando o usuário clicar no item_planta será levado para a tela com as informações da planta*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoritosFragment.navPlanta(planta.getId());
            }
        });


    }
}
