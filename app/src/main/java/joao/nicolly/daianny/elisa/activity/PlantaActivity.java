package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.Planta;

/*TODO: qual a necessidade do scrollview em planta?
*   se é necessária lá, porque não é necessária na activity_preparo?*/

public class PlantaActivity extends AppCompatActivity {
    int id;
    Button btnPreparos;
    Planta planta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);

        Intent i = getIntent();
        this.id = i.getIntExtra("id", 0);


        //Ao cliclar no botão preparos o usuário navegará para Preparos Activity via Intent
        btnPreparos = findViewById(R.id.btnPreparos);
        btnPreparos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlantaActivity.this,PreparosActivity.class);
                i.putExtra("preparos", (ArrayList) planta.getPreparos());
                startActivity(i);
            }
        });
    }
}