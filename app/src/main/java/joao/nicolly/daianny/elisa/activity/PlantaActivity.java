package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.viewModel.CadastroViewModel;
import joao.nicolly.daianny.elisa.model.objetos.Planta;

/*TODO: qual a necessidade do scrollview em planta?
*   se é necessária lá, porque não é necessária na activity_preparo?*/

public class PlantaActivity extends AppCompatActivity {

    int id;
    Button btnPreparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);

        Intent i = getIntent();
        this.id = i.getIntExtra("id", 0);

        CadastroViewModel.PlantaViewModel plantaViewModel = new ViewModelProvider(this).get(CadastroViewModel.PlantaViewModel.class);

        //método getPlantasDetailLd retorna o livedata
        LiveData<Planta> planta= plantaViewModel.getPlanta(id);
            planta.observe(this, new Observer<Planta>() {
                @Override
                public void onChanged(Planta planta) {
                    if(planta !=null){
                        TextView tvName = findViewById(R.id.tvName);
                        tvName.setText(planta.getNome());

                        ImageView imgvPlanta = findViewById(R.id.imgvPlanta);
                        Picasso.with(getApplicationContext())
                                .load(planta.getImagem())
                                .into(imgvPlanta);

                        TextView tvDesc = findViewById(R.id.tvDesc);
                        tvDesc.setText(planta.getDesc());

                    }

                }
            });
        //Favoritando plantas
        ImageButton imgbtnFavoritar = findViewById(R.id.imgbtnFavoritar);
        LiveData<Boolean> fav;
        imgbtnFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //Navegação para PreparosActivity
        btnPreparo = findViewById(R.id.btnPreparo);
        btnPreparo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlantaActivity.this, PreparosActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        //Navegação para ComentandoActivity
        Button btnComent = findViewById(R.id.btnComent);
        btnComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlantaActivity.this, ComentandoActivity.class);
                startActivity(i);
            }
        });
    }
}