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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.objetos.Planta;
import joao.nicolly.daianny.elisa.model.viewModel.EhFavoritoViewModel;
import joao.nicolly.daianny.elisa.model.viewModel.FavoritandoViewModel;
import joao.nicolly.daianny.elisa.model.viewModel.PlantaDetailViewModel;


public class PlantaActivity extends AppCompatActivity {

    int id;
    Button btnPreparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);

        Intent i = getIntent();
        this.id = i.getIntExtra("id", 0);

        PlantaDetailViewModel plantaDetailViewModel = new ViewModelProvider(this).get(PlantaDetailViewModel.class);

        //método getPlantasDetailLd retorna o livedata
        LiveData<Planta> planta= plantaDetailViewModel.getPlanta(id);
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
        /**No caso do botão favoritar ser acionado enviaremos uma requisião ao banco de dados */
        imgbtnFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //para impedir multiplas requisições e bug no código impedimos o usuário de clicar mais de uma vez
                //antes da requisição ao banco de dados ser concluida;
                imgbtnFavoritar.setClickable(false);
                favoritandoPlantaEscolhida();
                imgbtnFavoritar.setClickable(true);

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
    private void favoritandoPlantaEscolhida(){
        EhFavoritoViewModel ehFavoritoViewModel = new ViewModelProvider(this).get(EhFavoritoViewModel.class);
        LiveData<Boolean> ehFav = ehFavoritoViewModel.ehPlantaFavorita(id);//todo:fazer  esPlanta no viewModel

        FavoritandoViewModel favoritandoViewModel = new ViewModelProvider(this).get(FavoritandoViewModel.class);
        ehFav.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    /**é no view model que haverá a chamada para função de requisição do Repository requisição*/
                    LiveData<Boolean> fav = favoritandoViewModel.favoritandoPlanta(id);
                    desfavoritando(fav);

                } else{
                    /**é no view model que haverá a chamada para função de requisição do Repository requisição*/
                    LiveData<Boolean> fav = favoritandoViewModel.favoritandoPlanta(id);
                    favoritando(fav);
                }
            }
        });

    }
    private void desfavoritando(LiveData<Boolean> fav){
        fav.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(PlantaActivity.this,"Planta removida de favoritos!",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(PlantaActivity.this,"Ocorreu algum erro!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void favoritando(LiveData<Boolean> fav){
        fav.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(PlantaActivity.this,"Planta favoritada com sucesso!",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(PlantaActivity.this,"Não foi possível favoritar planta!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}