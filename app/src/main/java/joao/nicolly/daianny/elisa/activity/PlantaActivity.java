package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.PlantaViewModel;

/*TODO: qual a necessidade do scrollview em planta?
*   se é necessária lá, porque não é necessária na activity_preparo?*/

public class PlantaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);

        PlantaViewModel plantaActivity= new ViewModelProvider(this).get(PlantaViewModel.class);

    }
}