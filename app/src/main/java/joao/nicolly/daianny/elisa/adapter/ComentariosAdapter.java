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
import joao.nicolly.daianny.elisa.activity.ComentandoActivity;
import joao.nicolly.daianny.elisa.model.objetos.Comentario;

public class ComentariosAdapter extends PagingDataAdapter<Comentario,MyViewHolder> {
    ComentandoActivity comentandoActivity;
    public ComentariosAdapter(ComentandoActivity comentandoActivity, @NonNull DiffUtil.ItemCallback<Comentario> diffCallback) {
        super(diffCallback);
        this.comentandoActivity = comentandoActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_comentarioplanta, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comentario comentario = this.getItem(position);

        // Colocando a imagem do autor
        ImageView imgUserComent = holder.itemView.findViewById(R.id.imgUserComent);
        if(comentario.getFotoAutor().isEmpty()){
            Picasso.with(comentandoActivity.getApplicationContext())
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/default_ImageUser.jpg")
                    .into(imgUserComent);
        }else{
            Picasso.with(comentandoActivity.getApplicationContext())
                    .load(comentario.getFotoAutor())
                    .into(imgUserComent);
        }

        // colocando o UserName do autor no comentario
        TextView tvNomeAutor = holder.itemView.findViewById(R.id.tvNomeAutor);
        tvNomeAutor.setText(comentario.getAutor());

        TextView tvComentario = holder.itemView.findViewById(R.id.tvComentario);
        tvComentario.setText(comentario.getComentario());

    }
}
