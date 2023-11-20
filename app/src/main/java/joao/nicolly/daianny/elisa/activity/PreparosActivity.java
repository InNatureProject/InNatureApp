package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.adapter.PreparosAdapter;
import joao.nicolly.daianny.elisa.model.TipoPreparo;

public class PreparosActivity extends AppCompatActivity {

    //Variáveis
    ArrayList<TipoPreparo> preparos;
    RecyclerView rvPreparos;
    PreparosAdapter preparosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparos);

        //Aqui pegamos o id recevido pelo intent e o guardamos
        Intent i = getIntent();
        this.preparos = i.getParcelableArrayListExtra("preparos", 0);

        //pegamos o recicleView
        rvPreparos = findViewById(R.id.rvPreparos);
        rvPreparos.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPreparos.setLayoutManager(layoutManager);

        preparosAdapter = new PreparosAdapter(this);


    }
}