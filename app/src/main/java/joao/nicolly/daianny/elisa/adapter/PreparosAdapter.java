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

import joao.nicolly.daianny.elisa.activity.PreparosActivity;
import joao.nicolly.daianny.elisa.model.objetos.TipoPreparo;
import joao.nicolly.daianny.elisa.R;

public class PreparosAdapter extends PagingDataAdapter<TipoPreparo,MyViewHolder> {

    /** Para conseguirmos fazer o intent é necessário que armazenemos o PreparosActivity aqui
     * Também será necessário fazer o método intent no PreparosActivity*/

    PreparosActivity preparosActivity;
    public PreparosAdapter(PreparosActivity preparosActivity, @NonNull DiffUtil.ItemCallback<TipoPreparo> diffCallback) {
        super(diffCallback);
        this.preparosActivity = preparosActivity;
    }

    /**
     * Cria os elementos de UI referente a um item da lista
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_tipopreparo, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TipoPreparo tipoPreparo = this.getItem(position);

        // preenche o campo de nome
        TextView tvTipoPreparo = holder.itemView.findViewById(R.id.tvTipoPreparo);
        tvTipoPreparo.setText(tipoPreparo.getNome());

        ImageView imvTipoP = holder.itemView.findViewById(R.id.imvTipoP);
        if(tipoPreparo.getCod_preparo().equals("Chá")){
            Picasso.with(preparosActivity.getApplicationContext())
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/cha.avif")
                    .into(imvTipoP);
        }
        if(tipoPreparo.getCod_preparo().equals("Banho")){
            Picasso.with(preparosActivity.getApplicationContext())
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/Banho.webp")
                    .into(imvTipoP);
        }
        if(tipoPreparo.getCod_preparo().equals("Inalação")){
            Picasso.with(preparosActivity.getApplicationContext())
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/inalacao.webp")
                    .into(imvTipoP);
        }
        if(tipoPreparo.getCod_preparo().equals("Compressa")){
            Picasso.with(preparosActivity.getApplicationContext())
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/Compressa.webp")
                    .into(imvTipoP);
        }


        // ao clicar em um item da lista, navegamos para a tela que mostra os detalhes do produto
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preparosActivity.startReceitaPreparoActivity(tipoPreparo.getId(),tipoPreparo.getIdTipoPreparo());
            }
        });

    }
}
