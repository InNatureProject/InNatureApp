package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.PlantaViewModel;

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

        //Navegação para Preparos Activity
        btnPreparo = findViewById(R.id.btnPreparo);
        btnPreparo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlantaActivity.this, PreparosActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        PlantaViewModel plantaActivity= new ViewModelProvider(this).get(PlantaViewModel.class);

    }
}