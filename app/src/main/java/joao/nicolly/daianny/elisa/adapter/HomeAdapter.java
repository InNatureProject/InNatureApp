package joao.nicolly.daianny.elisa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.squareup.picasso.Picasso;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.fragment.HomeFragment;
import joao.nicolly.daianny.elisa.model.Planta;
import kotlinx.coroutines.CoroutineDispatcher;

public class HomeAdapter extends PagingDataAdapter<Planta,MyViewHolder> {
    HomeFragment homeFragment;

    //CONSTRUTOR
    public HomeAdapter(@NonNull DiffUtil.ItemCallback<Planta> diffCallback, HomeFragment homeFragment) {
        super(diffCallback);
        this.homeFragment = homeFragment;
    }

    //MÉTODOS
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

        //Utilizamos o picasso para baixar a imagem da url e colocar dentro do imageView
        //passamos o contexto, a url e o image view
        Picasso.with(homeFragment.getContext())
                .load(planta.getImagem())
                .into(imgvImagemPlanta);

        //colocando o nome
        TextView tvNome = holder.itemView.findViewById(R.id.tvNome);
        tvNome.setText(planta.getNome());


        /**quando o usuário clicar no item_planta será levado para a tela com as informações da planta*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.navPlanta(planta.getId());
                return false;
            }
        });



    }
}
